(ns demo.httpkit.main
  (:require [org.httpkit.server :as http]))

(defn -main [& args]
  (http/run-server #(do
                      (println %)  ;; 요청 객체가 전달됨을 표시, 출력하지 않더라도 인자를 받아야 함(ArityException 발생)
                      {:status 200
                       :headers {"Content-Type" "text/html"}
                       :body "Hello http-kit"})
                   {:port 8080}))