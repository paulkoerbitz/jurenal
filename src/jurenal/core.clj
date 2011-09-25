(ns jurenal.core
  (:use
    compojure.core
    jurenal.views
    ;; cljs-devmode.ring-middleware
    ;; ring.adapter.jetty
    [appengine-magic.multipart-params :only [wrap-multipart-params]])
  (:require 
    [appengine-magic.core :as ae]
    [appengine-magic.services.datastore :as ds]
    [appengine-magic.services.user :as usr]
    [ring.util.response :as response]))

(defroutes jurenal-app-handler
  (GET "/" [] (index))
  (GET "/create" [] (create-post)) 
  (POST "/update" _ (wrap-multipart-params update-post))
  (GET "/logout" [] (response/redirect (usr/logout-url)))
  (GET ["/:slug" :slug #"[-\w]+"] [slug] (show-post slug))
  (GET ["/edit/:slug" :slug #"[-\w-]+"] [slug] (edit-post slug))
  (GET ["/delete/:slug" :slug #"[-\w]+"] [slug] (delete-post slug))
  (ANY "*" _ (respond-404)))

(ae/def-appengine-app jurenal-app #'jurenal-app-handler)
 
;; (def jurenal-app
;;   (into jurenal {:handler (wrap-cljs-forward (:handler jurenal) "/media/cljs")}))
;; (ae/serve jurenal-app)
(ae/serve jurenal-app)
