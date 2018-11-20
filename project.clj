(defproject comp "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [reagent "0.8.1"]
                 [org.clojure/clojurescript "1.10.439"]
                 [metosin/reitit-schema "0.2.8"]
                 [metosin/reitit-frontend "0.2.8"]
                 [fipp "0.6.12"]]

  :plugins [[lein-cljsbuild "1.1.7"]]

  :profiles {:dev {:dependencies   [[binaryage/devtools "0.9.10"]]
                   :plugins        [[lein-figwheel "0.5.17"]]
                   :resource-paths ["target/cljsbuild"]}}

  :clean-targets ^{:protect false} ["resources/public/cljs" :target-path]

  :cljsbuild {:builds
              [{:id           "app"
                :figwheel     true
                :source-paths ["src"]
                :compiler     {:main       comp.core
                               :asset-path "/cljs/out"
                               :output-to  "target/cljsbuild/public/cljs/app.js"
                               :output-dir "target/cljsbuild/public/cljs/out"
                               :preloads   [devtools.preload]}}
               {:id           "min"
                :source-paths ["src"]
                :compiler     {:main            comp.core
                               :output-to       "resources/public/cljs/app.js"
                               :output-dir      "target/cljsbuild/public/cljs/min"
                               :externs         ["resources/public/js/externs"]
                               :optimizations   :advanced
                               :closure-defines {"goog.DEBUG" false}
                               :pretty-print    false}}]})
