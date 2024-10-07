(ns hello-clojure.control-structures)

(defn if-example [x]
  (if (> x 0)
    "Positive"
    "Non-positive"))

(defn when-example [x]
  (when (even? x)
    (str x " is even and its double is " (* x 2))))

(defn cond-example [x]
  (cond
    (< x 0) "Negative"
    (> x 0) "Positive"
    :else "Zero"))

(defn case-example [x]
  (case x
    1 "One"
    2 "Two"
    3 "Three"
    "Other"))

(defn demonstrate-control-structures []
  (println "If example:" (if-example 5))
  (println "When example:" (when-example 4))
  (println "When example:" (when-example 5))
  (println "Cond example:" (cond-example 0))
  (println "Cond example:" (cond-example 2))
  (println "Case example:" (case-example 2)))