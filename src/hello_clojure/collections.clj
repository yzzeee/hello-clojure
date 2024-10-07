(ns hello-clojure.collections)

(defn map-example [numbers]
  (map #(* % 2) numbers))

(defn filter-example [numbers]
  (filter even? numbers))

(defn reduce-example [numbers]
  (reduce + numbers))

(defn for-example []
  (for [x (range 3)
        y (range 3)
        :when (< y x)]
    [x y]))

;;(let [bindings*]
;;  expressions*)
(defn demonstrate-collections []
  (let [numbers [1 2 3 4 5]]
    (println "Original numbers:" numbers)
    (println "Map example (double each):" (map-example numbers))
    (println "Filter example (even only):" (filter-example numbers))
    (println "Reduce example (sum all):" (reduce-example numbers))
    (println "For example (combinations):" (for-example))))