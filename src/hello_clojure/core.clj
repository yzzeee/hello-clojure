(ns hello-clojure.core
  (:require [hello-clojure.data-types]
            [hello-clojure.functions :as f :refer [greet]]
            [hello-clojure.control-structures :as cs]
            [hello-clojure.collections :refer [demonstrate-collections]]
            [ns-01.fn-vs-sharp :as fs]
            )
  (:gen-class))

(defn -main
  "애플리케이션 진입점"
  [& args]
  (println "\n===== 기본 데이터 타입 =====")
  (hello-clojure.data-types/demonstrate-data-types)

  (println "\n=== 함수 사용 ===")
  (f/demonstrate-functions) ; 직접 호출
  (println (greet "Clojure"))  ; 직접 호출
  (println (f/square 5))  ; 네임스페이스 별칭 사용

  (println "\n=== 제어 구조 ===")
  (cs/demonstrate-control-structures)

  (println "\n=== 컬렉션 처리 ===")
  (demonstrate-collections)

  (println "\n=== fn vs sharp =====")
  (println (fs/add-or-subtract-fn 5 3))   ;; 결과: 8
  (println (fs/add-or-subtract-fn 3 5))   ;; 결과: -2

  ;; #()로 정의된 함수 호출
  (println (fs/add-or-subtract-sharp 5 3))  ;; 결과: 8
  (println (fs/add-or-subtract-sharp 3 5))  ;; 결과: -2

  ;; fn으로 복잡한 연산 실행
  (println (fs/complex-operation-fn 5 3))  ;; 결과: 8/15

  ;; #()로 복잡한 연산 실행
  (println (fs/complex-operation-sharp 5 3))  ;; 결과: 8/15
  )
