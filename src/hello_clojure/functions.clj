(ns hello-clojure.functions)

(defn greet
  "간단한 인사 함수"
  [name]
  (str "Hello, " name "!"))

(defn square
  "숫자의 제곱을 반환"
  [x]
  (* x x))

(defn sum
  "가변 인자를 받아 모두 더함"
  [& numbers]
  (apply + numbers))

(defn demonstrate-functions []
  (println "Greeting:" (greet "Clojure"))
  (println "Square of 5:" (square 5))
  (println "Sum of 1, 2, 3, 4:" (sum 1 2 3 4)))