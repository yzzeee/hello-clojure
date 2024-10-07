(ns hello-clojure.data-types)

(defn demonstrate-data-types []
  ; 숫자 (Numbers)
  (println "--- 숫자 ---")
  (println "정수:" 42)        ; 64비트 정수
  (println "부동소수점:" 3.14) ; 64비트 부동소수점
  (println "분수:" 22/7)      ; 정확한 분수 표현
  (println "BigInt:" 9223372036854775808N) ; 임의 정밀도 정수
  (println "BigDecimal:" 1.23M) ; 임의 정밀도 십진수

  ; 문자열 (Strings)
  (println "\n--- 문자열 ---")
  (println "문자열:" "Hello, Clojure!") ; 불변 문자열
  (println "문자:" \A)        ; 단일 문자

  ; 불리언 (Booleans)
  (println "\n--- 불리언 ---")
  (println "참:" true)        ; 논리 참
  (println "거짓:" false)     ; 논리 거짓

  ; 심볼과 키워드 (Symbols and Keywords)
  (println "\n--- 심볼과 키워드 ---")
  (println "심볼:" 'my-symbol) ; 주로 코드에서 식별자로 사용
  (println "키워드:" :name)    ; 효율적인 룩업을 위한 식별자

  ; nil (null에 해당)
  (println "\n--- nil ---")
  (println "nil:" nil)        ; '값 없음'을 나타냄

  ; 컬렉션 (Collections)
  (println "\n--- 컬렉션 ---")
  (println "리스트:" '(1 2 3 4 5))  ; 연결 리스트, 순차적 접근에 효율적
  (println "벡터:" [1 2 3 4 5])     ; 인덱스로 빠른 접근이 가능한 배열과 유사
  (println "맵:" {:name "Alice" :age 30}) ; 키-값 쌍의 연관 배열
  (println "집합:" #{1 2 3 4 5})    ; 중복 없는 고유 값의 컬렉션

  ; 정규 표현식 (Regular Expressions)
  (println "\n--- 정규 표현식 ---")
  (println "정규 표현식:" #"[A-Z]\w+") ; 문자열 패턴 매칭을 위한 객체

  ; 함수 (Functions)
  (println "\n--- 함수 ---")
  (println "익명 함수:" (fn [x] (* x x))) ; 이름 없는 함수
  (println "함수 리터럴:" #(* % %))       ; 간단한 함수를 위한 축약형

  ; 원자 (Atoms) - 동시성을 위한 참조 타입
  (println "\n--- 원자 ---")
  (println "원자:" (atom 5))  ; 동시성 관리를 위한 변경 가능한 참조

  ; 지연 시퀀스 (Lazy Sequences)
  (println "\n--- 지연 시퀀스 ---")
  (println "지연 시퀀스:" (take 5 (range))) ; 필요할 때 계산되는 시퀀스
  )