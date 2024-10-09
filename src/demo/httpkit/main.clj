(ns demo.httpkit.main
  (:require [org.httpkit.server :as http]))

;; 서버 핸들러를 저장하는 atom
(defonce server-state (atom {:server nil :port nil}))

;; 요청을 처리하는 명시적인 핸들러 함수 정의
(defn handle-request [req]
  (println req)                                             ;; 요청 객체 출력
  (Thread/sleep 2000)  ; 각 요청을 2초 지연
  (println "Request processed:" (:uri req))
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello http-kit"})

(defn available-port []
  (with-open [socket (java.net.ServerSocket. 0)]
    (.getLocalPort socket)))

;; 서버 시작 함수
(defn start-server
  ([] (start-server (available-port)))
  ([port]
   (if (:server @server-state)
     (println "서버가 이미 실행 중입니다. 포트:" (:port @server-state))
     ;; 서버 실행 및 핸들러 저장
     (let [handler (http/run-server handle-request {:port port})]
       (swap! server-state assoc :server handler :port port)
       (println "서버가" port "번 포트에서 실행 중입니다.")))))

;; 서버 중지 함수
;; 서버는 새로운 연결을 즉시 거부합니다.
;; 이미 처리 중인 요청에 대해서만 최대 1초 동안 완료를 기다립니다.
;; 진행 중인 요청이 1초 이내에 모두 완료되면 1초를 기다리지 않고 즉시 종료됩니다.
;; 완료되지 않은 요청은 강제 종료됩니다.
;; 서버 리소스(스레드 풀, 연결 등)를 정리합니다.
(defn stop-server-gracefully []
  (if-let [stop-fn (:server @server-state)]
    (do
      (println "서버를 gracefully하게 중지합니다... (최대 대기 시간: 3초)")
      (let [start-time (System/currentTimeMillis)
            shutdown-future (future (stop-fn :timeout 3000))
            _ @shutdown-future
            end-time (System/currentTimeMillis)
            total-duration (- end-time start-time)]
        (reset! server-state {:server nil :port nil})
        (println (str "서버가 중지되었습니다. 총 소요 시간: " total-duration "ms"
                      (if (>= total-duration 3000)
                        " (최대 대기 시간 도달)"
                        (str " (최대 대기 시간: 3000ms)"))))))
    (println "서버가 실행 중이지 않습니다.")))

;; 서버 중지 함수
;; 서버는 1초 동안 정상적으로 계속 운영됩니다.
;; 새로운 연결과 요청을 계속 받습니다.
;; 1초 후 stop-server가 호출되면 서버가 즉시 종료되며, 진행 중인 요청들이 강제로 중단될 수 있습니다.
(defn stop-server-with-sleep []
  (if-let [stop-fn (:server @server-state)]
    (do
      (println "서버를 일정 시간 후 중지합니다... (대기 시간: 3초)")
      (let [start-time (System/currentTimeMillis)
            _ (Thread/sleep 3000)
            stop-start-time (System/currentTimeMillis)
            _ (stop-fn)
            end-time (System/currentTimeMillis)
            total-duration (- end-time start-time)
            stop-duration (- end-time stop-start-time)]
        (reset! server-state {:server nil :port nil})
        (println (str "서버가 중지되었습니다. 총 소요 시간: " total-duration "ms"
                      " (대기 시간: 3000ms, 실제 중지 시간: " stop-duration "ms)"))))
    (println "서버가 실행 중이지 않습니다.")))

;; 서버 재시작 함수
(defn restart-server []
  (println "서버를 재시작합니다...")
  (stop-server-gracefully)
  (start-server))

;; 메인 함수에서 http-kit 서버 실행
(defn -main [& args]
  (restart-server))

; 테스트 함수들
(defn test-graceful-shutdown []
  (let [port (start-server)]
    (Thread/sleep 1000)  ; 서버 시작 대기
    (future (dotimes [_ 5]
              (try
                (slurp (str "http://localhost:" port))
                (catch Exception e
                  (println "Request failed:" (.getMessage e))))))
    (Thread/sleep 1000)  ; 요청 시작 대기
    (stop-server-gracefully)))

(defn test-sleep-shutdown []
  (let [port (start-server)]
    (Thread/sleep 1000)  ; 서버 시작 대기
    (future (dotimes [_ 5]
              (try
                (slurp (str "http://localhost:" port))
                (catch Exception e
                  (println "Request failed:" (.getMessage e))))))
    (Thread/sleep 1000)  ; 요청 시작 대기
    (stop-server-with-sleep)))

; REPL 종료 시 서버 중지를 위한 shutdown hook
(.addShutdownHook (Runtime/getRuntime)
                  (Thread. ^Runnable (fn []
                                       (println "REPL 종료. 서버를 중지합니다.")
                                       (stop-server-gracefully))))

;;(demo.httpkit.main/start-server)
;;; => 서버가 12345 번 포트에서 실행 중입니다.
;;

;; for i in {1..10}; do curl http://localhost:12345 & done

;;(demo.httpkit.main/stop-server-with-sleep)
;;; => 서버를 일정 시간 후 중지합니다... (대기 시간: 3초)
;;; => 서버가 중지되었습니다. 총 소요 시간: 3005ms (대기 시간: 3000ms, 실제 중지 시간: 5ms)
;;
;;(demo.httpkit.main/start-server)
;;; => 서버가 12346 번 포트에서 실행 중입니다.
;;
;;(demo.httpkit.main/stop-server-gracefully)
;;; => 서버를 gracefully하게 중지합니다... (최대 대기 시간: 3초)
;;; => 서버가 중지되었습니다. 총 소요 시간: 15ms (최대 대기 시간: 3000ms)
