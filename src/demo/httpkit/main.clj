(ns demo.httpkit.main
  (:require [org.httpkit.server :as http]))

;; 서버 핸들러를 저장하는 atom
(defonce server (atom nil))

;; 요청을 처리하는 명시적인 핸들러 함수 정의
(defn handle-request [req]
  (println req)                                             ;; 요청 객체 출력
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello http-kit"})

;; 서버 시작 함수
(defn start-server []
  (if @server
    (println "서버가 이미 실행 중 입니다.")
    (do
      (println "서버 시작...")
      ;; 서버 실행 및 핸들러 저장
      (let [handler (http/run-server handle-request {:port 8080})]
        (reset! server handler)
        (println "서버 핸들러가 atom에 저장되었습니다:" @server))
      (println "서버가 8080번 포트에서 실행 중입니다."))))

;; 서버 중지 함수
(defn stop-server []
  (if @server
    (do
      (println "서버가 3000ms 후 중지됩니다...")
      (Thread/sleep 3000)  ;; 3000ms 대기
      ;; 서버 핸들러 호출
      (@server)
      (reset! server nil)
      (println "서버가 중지되었습니다."))
    (println "서버가 실행 중이지 않습니다.")))

;; 서버 재시작 함수
(defn restart-server []
  (println "서버를 재시작합니다...")
  (stop-server)                                             ;; 서버 중지
  (start-server))                                           ;; 서버 재시작

;; 메인 함수에서 http-kit 서버 실행
(defn -main [& args]
  (restart-server))
