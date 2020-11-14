(ns leiningen.new.reitit
  (:require [leiningen.new.common :refer :all]))

(def reitit-assets
  [["{{resource-path}}/docs/docs.md" "reitit/resources/docs.md"]
   ["{{backend-path}}/{{sanitized}}/routes/home.clj" "reitit/src/home.clj"]])

(defn reitit-features [[assets options :as state]]
  (if (some #{"+reitit"} (:features options))
    [(into (remove-conflicting-assets assets "home.clj" "docs.md") reitit-assets)
     (-> options
         (append-options :dependencies [['metosin/reitit "0.5.10"]
                                        ['metosin/jsonista "0.2.7"]
                                        ['com.fasterxml.jackson.core/jackson-core "2.11.3"]])
         (assoc :reitit true))]
    state))
