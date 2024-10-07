(ns hello-clojure.core
  (:require [hello-clojure.data-types])
  (:gen-class))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn greet
  "간단한 인사 함수"                                               ;; docstring : ex) (doc greet) 형태로 사용
  [name]
  (str "안녕하세요, " name "님!"))

(defn -main
  "애플리케이션 진입점"
  [& args]
  (println (greet "Clojure Beginner"))

  (println "===== 기본 데이터 타입 =====")
  (hello-clojure.data-types/demonstrate-data-types)
  )
