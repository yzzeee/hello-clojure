(ns demo.httpkit.main
  (:require [org.httpkit.server :as http]))

;; 요청을 처리하는 명시적인 핸들러 함수 정의
(defn handle-request [req]
  (println req)  ;; 요청 객체 출력
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello http-kit"})

;; 메인 함수에서 http-kit 서버 실행
(defn -main [& args]
  (http/run-server handle-request {:port 8080}))
