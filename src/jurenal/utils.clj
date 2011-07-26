(ns jurenal.utils
  )

(defmacro check-auth 
  "Executes body if user is logged in, redirects to login-url if not."
  [& body]
  `(if (logged-in?) (do ~@body) (response/redirect "/"))) 