;; Die ersten drei Zeilen dieser Datei wurden von DrRacket eingefügt. Sie enthalten Metadaten
;; über die Sprachebene dieser Datei in einer Form, die DrRacket verarbeiten kann.
#reader(lib "vanilla-reader.rkt" "deinprogramm" "sdp")((modname fp-SdP) (read-case-sensitive #f) (teachpacks ()) (deinprogramm-settings #(#f write repeating-decimal #f #t none explicit #f ())))
;; =================
;; Data Definitions
;; =================

;; TrafficLight is one of:
;; - "red"
;; - "yellow"
;; - "green"
;; interp. represents states of a traffic light
(define RED "red")
(define YELLOW "yellow")
(define GREEN "green")

; A TrafficLight is either RED, YELLOW, or GREEN
(define TrafficLight
  (signature (enum RED YELLOW GREEN)))


;; Template
(: fn-for-traffic-light (TrafficLight -> ...))
(define fn-for-traffic-light
  (lambda (tl)
    (cond
      ((string=? tl RED)    ...)
      ((string=? tl YELLOW) ...)
      ((string=? tl GREEN)  ...))))





















;; A person has
;; - a name (string)
;; - a favorite food (string)
(: make-person (string string -> person))
(: person? (any -> boolean))
(: person-name (person -> string))
(: person-favorite-food (person -> string))
(define-record person
  make-person
  person?
  (person-name          string)
  (person-favorite-food string))


;; Template:
(: fn-for-person (person -> ...))
(define fn-for-person
  (lambda (p)
    (... (person-name p) ...
     ... (person-favorite-food p ...))))






















; A crossing has 
; - a horizontal traffic light (TrafficLight)
; - a vertical traffic light (TrafficLight)
(define-record crossing
  make-crossing
  crossing?
  (crossing-horizontal TrafficLight)
  (crossing-vertical   TrafficLight))





























;; =================
;; Functions
;; =================

;; Higher-order function example (similar to Scala's compose)
;; Number (Number -> Number) (Number -> Number) -> Number
;; Applies function g to the result of applying function f to n
(check-expect (my-compose 42 add1 (lambda (x) (* x 2))) 86)
(define my-compose
  (lambda (n f g)
    (g (f n))))

;; Switches traffic light to next state in cycle
(: switch-traffic-light (TrafficLight -> TrafficLight))
(check-expect (switch-traffic-light RED) GREEN)
(check-expect (switch-traffic-light YELLOW) RED)
(check-expect (switch-traffic-light GREEN) YELLOW)
(define switch-traffic-light
  (lambda (tl)
    (cond
     ((string=? tl RED) GREEN)
     ((string=? tl YELLOW) RED)
     ((string=? tl GREEN) YELLOW))))

;; List processing examples (similar to Scala's immutable collections)
;; List[Number] -> Number
;; Computes sum of all numbers in list
(check-expect (sum empty) 0)
(check-expect (sum (list 1 2 3)) 6)
(define sum
  (lambda (lst)
    (match lst
      (empty       0)
      ((cons x xs) (+ x (sum xs))))))

; ohne Pattern Matching:
;(define sum
;  (lambda (lst)
;    (cond
;      ((empty? lst) 0)
;      ((cons?  lst) (+ (first lst)
;                       (sum (rest lst)))))))







;; Determines if traffic light configuration is allowed
;; Returns false if both lights are green or if one is yellow and other is green
;; (: allowed-config (Crossing -> boolean))
(check-expect (allowed-config? (make-crossing GREEN GREEN))  #f)
(check-expect (allowed-config? (make-crossing YELLOW GREEN)) #f)
(check-expect (allowed-config? (make-crossing RED GREEN))    #f)
(define allowed-config
  (lambda (c)
    (cond
      ((and (string=? (crossing-horizontal c) GREEN)
            (string=? (crossing-vertical c) GREEN)) #f)
      ((and (string=? (crossing-horizontal c) YELLOW)
            (string=? (crossing-vertical c) GREEN)) #f)
      ((and (string=? (crossing-horizontal c) GREEN)
            (string=? (crossing-vertical c) YELLOW)) #f)
      (else #t))))

;; Example structures
(: p1 person)
(: c1 Crossing)
(define p1 (make-person "Jonathan" "Burgers"))
(define c1 (make-crossing RED GREEN))