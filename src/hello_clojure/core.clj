(ns hello-clojure.core
  (:require [hello-clojure.data-types]
            [hello-clojure.functions :as f :refer [greet]])
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
  )
