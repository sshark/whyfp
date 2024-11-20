package org

import scala.util.{Failure, Success, Try}

package object acme:

  import scala.annotation.tailrec

  @tailrec
  def retry[A](maxAttempt: Int, err: Throwable)(action: Int => Try[A]): Try[(A, Int)] =
    if maxAttempt <= 0 then Failure[(A, Int)](new IllegalArgumentException("maxAttempt must be greater than 0"))
    else if maxAttempt == 1 then action(1).map(a => (a, 1))
    else
      action(maxAttempt) match
        case Success(value) => Success((value, maxAttempt))
        case Failure(_)   => retry(maxAttempt - 1, err)(action)
