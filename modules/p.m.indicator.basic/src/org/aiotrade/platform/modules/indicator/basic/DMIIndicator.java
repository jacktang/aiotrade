/*
 * Copyright (c) 2006-2007, AIOTrade Computing Co. and Contributors
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  o Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer. 
 *    
 *  o Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution. 
 *    
 *  o Neither the name of AIOTrade Computing Co. nor the names of 
 *    its contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission. 
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.aiotrade.platform.modules.indicator.basic;

import org.aiotrade.lib.math.timeseries.Var;
import org.aiotrade.lib.math.timeseries.computable.Opt;
import org.aiotrade.lib.math.timeseries.plottable.Plot;
import org.aiotrade.lib.indicator.AbstractContIndicator;
import org.aiotrade.lib.indicator.AbstractIndicator.DefaultOpt;
import org.aiotrade.lib.indicator.IndicatorName;

/**
 *
 * @author Caoyuan Deng
 */
@IndicatorName("DMI")
public class DMIIndicator extends AbstractContIndicator {
    
    Opt periodDi  = new DefaultOpt("Period DI",  6.0);
    Opt periodAdx = new DefaultOpt("Period ADX", 14.0);
    
    Var<Float> diPlus  = new DefaultVar("+DI",  Plot.Line);
    Var<Float> diMinus = new DefaultVar("-DI",  Plot.Line);
    Var<Float> adx     = new DefaultVar("ADX",  Plot.Line);
    Var<Float> adxr    = new DefaultVar("ADXR", Plot.Line);
    
    {
        _sname = "DMI";
        _lname = "Directional Movement Index";
        _grids = new Float[] {20f, 80f};
    }
    
    protected void computeCont(int begIdx) {
        for (int i = begIdx; i < _itemSize; i++) {
            
            diPlus .set(i, diPlus( i, periodDi));
            diMinus.set(i, diMinus(i, periodDi));
            adx    .set(i, adx(    i, periodDi, periodAdx));
            adxr   .set(i, adxr(   i, periodDi, periodAdx));
            
        }
    }
    
}
