(ns jurenal.app_servlet
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:use jurenal.core)
  (:use [appengine-magic.servlet :only [make-servlet-service-method]]))


(defn -service [this request response]
  ((make-servlet-service-method jurenal-app) this request response))
