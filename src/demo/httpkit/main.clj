(ns demo.httpkit.main
  (:require [org.httpkit.server :as http]))

(defn -main [& args]
  (http/run-server
    (fn [req]  ;; fn을 사용해 익명 함수로 요청 객체(req)를 받음
      (println req)  ;; 요청 객체 출력
      {:status 200
       :headers {"Content-Type" "text/html"}
       :body "Hello http-kit"})
    {:port 8080}))