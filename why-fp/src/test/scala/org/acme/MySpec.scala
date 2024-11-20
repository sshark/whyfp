package org.acme

import zio.{Scope, ZIO}
import zio.test._
import zio.test.Assertion._

@SuppressWarnings(Array("org.wartremover.warts.All"))
object MySpec extends ZIOSpecDefault {
  override def spec: Spec[TestEnvironment with Scope, Any] = suite("Example Spec")(
    test("Sample testing") {
      assertZIO(ZIO.succeed(1 + 1))(equalTo(2))
    }
  )

}
