(ns jurenal.utils
  (:import play.templates.JavaExtensions))

(JavaExtensions/slugify "A really really scary Title - Part 2")
;
;(ns jurenal.utils
;  (:import 'java.text.Normalizer
;           'java.text.Normalizer$Form) 
;(defn remove-accents [s]
;  (->> 
;    (Normalizer/normalize s Normalizer$Form/NFKC)
;    (replace-by #"[àáâãäåāąă]" "a")
;    (replace-by #"[çćčĉċ]" "c")
;    (replace-by #"[ďđð]" "d")
;    (replace-by #"[èéêëēęěĕė]" "e")
;    (replace-by #"[ƒſ]" "f")
;    (replace-by #"[ĝğġģ]" "g")
;    (replace-by #"[ĥħ]" "h")
;    (replace-by #"[ìíîïīĩĭįı]" "i")
;    (replace-by #"[ĳĵ]" "j")
;    (replace-by #"[ķĸ]" "k")
;    (replace-by #"[łľĺļŀ]" "l")
;    (replace-by #"[ñńňņŉŋ]" "n")
;    (replace-by #"[òóôõöøōőŏœ]" "o")
;    (replace-by #"[Þþ]" "p")
;    (replace-by #"[ŕřŗ]" "r")
;    (replace-by #"[śšşŝș]" "s")
;    (replace-by #"[ťţŧț]" "t")
;    (replace-by #"[ùúûüūůűŭũų]" "u")
;    (replace-by #"[ŵ]" "w")
;    (replace-by #"[ýÿŷ]" "y")
;    (replace-by #"[žżź]" "z")
;    (replace-by #"[æ]" "ae")
;    (replace-by #"[ÀÁÂÃÄÅĀĄĂ]" "A")
;    (replace-by #"[ÇĆČĈĊ]" "C")
;    (replace-by #"[ĎĐÐ]" "D")
;    (replace-by #"[ÈÉÊËĒĘĚĔĖ]" "E")
;    (replace-by #"[ĜĞĠĢ]" "G")
;    (replace-by #"[ĤĦ]" "H")
;    (replace-by #"[ÌÍÎÏĪĨĬĮİ]" "I")
;    (replace-by #"[Ĵ]" "J")
;    (replace-by #"[Ķ]" "K")
;    (replace-by #"[ŁĽĹĻĿ]" "L")
;    (replace-by #"[ÑŃŇŅŊ]" "N")
;    (replace-by #"[ÒÓÔÕÖØŌŐŎ]" "O")
;    (replace-by #"[ŔŘŖ]" "R")
;    (replace-by #"[ŚŠŞŜȘ]" "S")
;    (replace-by #"[ÙÚÛÜŪŮŰŬŨŲ]" "U")
;    (replace-by #"[Ŵ]" "W")
;    (replace-by #"[ÝŶŸ]" "Y")
;    (replace-by #"[ŹŽŻ]" "Z")
;    (replace-by #"[ß]" "ss")))
;
;(defn slugify [s]
;  (->>
;    (remove-accents s)
;    (replace-by #"([a-z])'s([^a-z])" "$1s$2")
;    (replace-by #"[^\\w]" "-")
;    (replace-by #"-{2,}" "-")
;    (replace-by #"-+$" "")
;    (replace-by #"^-+" "")))