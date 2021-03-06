package org.aiotrade.lib.securities.model

import junit.framework.TestCase
import scala.collection.mutable
import junit.framework.Assert._
import java.text.SimpleDateFormat
import java.util.TimeZone

class TestExchangeUnit extends TestCase {

  private lazy val N   = Exchange.N
  private lazy val SS  = Exchange.SS
  private lazy val SZ  = Exchange.SZ
  private lazy val L   = Exchange.L
  private lazy val HK  = Exchange.HK
  private lazy val OQ  = Exchange.OQ
  
  private val timeToTradingStatus = mutable.Map[Long, SS.TradingStatus]()

  private val dateFormat = new SimpleDateFormat("yyyyMMddHHmmss")
  protected val timeZone = TimeZone.getTimeZone("Asia/Shanghai")


  override def setUp() {
    timeToTradingStatus.put(parseDateStr("20100915091000"), SS.PreOpen(parseDateStr("20100915091000"),9*60+10))
    timeToTradingStatus.put(parseDateStr("20100915091500"), SS.OpeningCallAcution(parseDateStr("20100915091500"),9*60+15))
    timeToTradingStatus.put(parseDateStr("20100915092000"), SS.OpeningCallAcution(parseDateStr("20100915092000"),9*60+20))
    timeToTradingStatus.put(parseDateStr("20100915092500"), SS.OpeningCallAcution(parseDateStr("20100915092500"),9*60+25))
    timeToTradingStatus.put(parseDateStr("20100915092700"), SS.Break(parseDateStr("20100915092700"),9*60+27))
    timeToTradingStatus.put(parseDateStr("20100915093000"), SS.Open(parseDateStr("20100915093000"),9*60+30))
    timeToTradingStatus.put(parseDateStr("20100915093500"), SS.Opening(parseDateStr("20100915093500"),9*60+35))
    timeToTradingStatus.put(parseDateStr("20100915113000"), SS.Opening(parseDateStr("20100915113000"),11*60+30))
    timeToTradingStatus.put(parseDateStr("20100915114500"), SS.UnknownStatus(parseDateStr("20100915114500"),11*60+45))
    timeToTradingStatus.put(parseDateStr("20100915130000"), SS.Opening(parseDateStr("20100915130000"),13*60+00))
    timeToTradingStatus.put(parseDateStr("20100915134500"), SS.Opening(parseDateStr("20100915134500"),13*60+45))
    timeToTradingStatus.put(parseDateStr("20100915150000"), SS.Close(parseDateStr("20100915150000"),15*60+00))
    timeToTradingStatus.put(parseDateStr("20100915150100"), SS.Close(parseDateStr("20100915150100"),15*60+01))
    timeToTradingStatus.put(parseDateStr("20100915150200"), SS.Close(parseDateStr("20100915150200"),15*60+02))
    timeToTradingStatus.put(parseDateStr("20100915150300"), SS.Closed(parseDateStr("20100915150300"),15*60+03))
    dateFormat.setTimeZone(timeZone)
  }

  def testTradStatus() {
    timeToTradingStatus foreach {
      x => assertEquals(SS.tradingStatusOf(x._1),x._2)
    }
  }




  def testStringAppend(){

    assertEquals("Hello world!", "Hello world!")

  }

  private def parseDateStr(yyyyMMddHHmmss : String) : Long = {
    val time = try {
      dateFormat.parse(yyyyMMddHHmmss).getTime
    } catch {case ex: Exception => 0L}

    time

  }


}
