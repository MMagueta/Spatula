(ns spatula.core
  (:gen-class)
  (:require [net.cgrand.enlive-html :as html]))

(def test-url "https://www.marcosmagueta.com")

(defn page-loader [link]
  (html/html-resource (java.net.URL. link)))

(defn -main
  [& args]
  (println
   (-> (page-loader test-url)
       (html/select [:a]))))
