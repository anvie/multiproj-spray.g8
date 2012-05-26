package com.$organization$.$app_name$

import cc.spray.Directives

/**
 * Copyright (C) 2011-2012 Ansvia Inc.
 * User: robin
 * Date: 5/27/12
 * Time: 12:08 AM
 *
 */

trait HelloService extends Directives {
  val helloService = {
    get {
      path("") {
        completeWith("hello world!!")
      }
    }
  }
}
