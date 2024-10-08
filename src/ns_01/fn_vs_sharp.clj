(ns ns-01.fn-vs-sharp)

;; fn을 사용한 익명 함수
(def add-or-subtract-fn
  (fn [x y]
    (if (> x y)
      (+ x y)
      (- x y))))

;; #()를 사용한 익명 함수
(def add-or-subtract-sharp #(if (> %1 %2) (+ %1 %2) (- %1 %2)))

;; fn을 사용한 복잡한 익명 함수
(def complex-operation-fn
  (fn [x y]
    (let [sum (+ x y)
          prod (* x y)]
      (/ sum prod))))

;; #()로 복잡한 계산 처리
(def complex-operation-sharp
  #(let [sum (+ %1 %2)     ;; sum = %1 + %2
         prod (* %1 %2)]   ;; prod = %1 * %2
     (/ sum prod)))        ;; sum을 prod로 나눔
