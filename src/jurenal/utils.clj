(ns jurenal.utils
  (:import (java.text Normalizer)
           (java.text Normalizer$Form)) 
  (:require [appengine-magic.services.user :as usr]
            [clojure.string :as str]))

(defn authorized? []
  (and (usr/user-logged-in?)
       (= (.getEmail (usr/current-user)) "paul.koerbitz@gmail.com")))

(defmacro check-auth 
  "Executes body if user is logged in, redirects to login-url if not."
  [& body]
  `(if (authorized?)
     (do ~@body)
     (response/redirect (usr/login-url))))

(defn remove-accents [s]
  (->
    (Normalizer/normalize s Normalizer$Form/NFKC)
    (str/replace #"[àáâãäåāąă]" "a")
    (str/replace #"[çćčĉċ]" "c")
    (str/replace #"[ďđð]" "d")
    (str/replace #"[èéêëēęěĕė]" "e")
    (str/replace #"[ƒſ]" "f")
    (str/replace #"[ĝğġģ]" "g")
    (str/replace #"[ĥħ]" "h")
    (str/replace #"[ìíîïīĩĭįı]" "i")
    (str/replace #"[ĳĵ]" "j")
    (str/replace #"[ķĸ]" "k")
    (str/replace #"[łľĺļŀ]" "l")
    (str/replace #"[ñńňņŉŋ]" "n")
    (str/replace #"[òóôõöøōőŏœ]" "o")
    (str/replace #"[Þþ]" "p")
    (str/replace #"[ŕřŗ]" "r")
    (str/replace #"[śšşŝș]" "s")
    (str/replace #"[ťţŧț]" "t")
    (str/replace #"[ùúûüūůűŭũų]" "u")
    (str/replace #"[ŵ]" "w")
    (str/replace #"[ýÿŷ]" "y")
    (str/replace #"[žżź]" "z")
    (str/replace #"[æ]" "ae")
    (str/replace #"[ÀÁÂÃÄÅĀĄĂ]" "A")
    (str/replace #"[ÇĆČĈĊ]" "C")
    (str/replace #"[ĎĐÐ]" "D")
    (str/replace #"[ÈÉÊËĒĘĚĔĖ]" "E")
    (str/replace #"[ĜĞĠĢ]" "G")
    (str/replace #"[ĤĦ]" "H")
    (str/replace #"[ÌÍÎÏĪĨĬĮİ]" "I")
    (str/replace #"[Ĵ]" "J")
    (str/replace #"[Ķ]" "K")
    (str/replace #"[ŁĽĹĻĿ]" "L")
    (str/replace #"[ÑŃŇŅŊ]" "N")
    (str/replace #"[ÒÓÔÕÖØŌŐŎ]" "O")
    (str/replace #"[ŔŘŖ]" "R")
    (str/replace #"[ŚŠŞŜȘ]" "S")
    (str/replace #"[ÙÚÛÜŪŮŰŬŨŲ]" "U")
    (str/replace #"[Ŵ]" "W")
    (str/replace #"[ÝŶŸ]" "Y")
    (str/replace #"[ŹŽŻ]" "Z")
    (str/replace #"[ß]" "ss")))

(defn slugify [s]
  (->
   (remove-accents s)
   (str/replace #"([a-z])'s([^a-z])" "$1s$2")
   (str/replace #"[^\\\w]" "-")
   (str/replace #"-{2,}" "-")
   (str/replace #"-+$" "")
   (str/replace #"^-+" "")))
