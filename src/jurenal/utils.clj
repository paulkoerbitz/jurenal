(ns jurenal.utils
  (:require [appengine-magic.services.user :as usr]))

(defn authorized? []
  (and (usr/user-logged-in?)
       (= (.getEmail (usr/current-user)) "paul.koerbitz@gmail.com")))

(defmacro check-auth 
  "Executes body if user is logged in, redirects to login-url if not."
  [& body]
  `(if (authorized?)
     (do ~@body)
     (response/redirect (usr/login-url))))