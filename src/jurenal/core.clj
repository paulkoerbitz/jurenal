(ns jurenal.core
  (:use
    compojure.core
    jurenal.views
    [appengine-magic.multipart-params :only [wrap-multipart-params]])
  (:require 
    [appengine-magic.core :as ae]
    [appengine-magic.services.datastore :as ds]
    [appengine-magic.services.user :as usr]
    [ring.util.response :as response]))

(defroutes jurenal-app-handler
  (GET "/logout" [] (response/redirect (usr/logout-url)))
  (GET "/" [] (index))
  (GET "/create" [] (create-post)) 
  (POST "/update" _ (wrap-multipart-params update-post))
  (GET ["/:slug" :slug #"[-\w]+"] [slug] (show-post slug))
  (GET ["/edit/:slug" :slug #"[-\w-]+"] [slug] (edit-post slug))
  (GET ["/delete/:slug" :slug #"[-\w]+"] [slug] (delete-post slug))
  (ANY "*" _ (respond-404)))

(ae/def-appengine-app jurenal-app #'jurenal-app-handler)
;(ae/serve jurenal-app)


