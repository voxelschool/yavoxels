/*
 * Copyright (C) 2013 Jason Taylor.
 * Released as open-source under the Apache License, Version 2.0.
 * 
 * ============================================================================
 * | Joise
 * ============================================================================
 * 
 * Copyright (C) 2013 Jason Taylor
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * ============================================================================
 * | Accidental Noise Library
 * | --------------------------------------------------------------------------
 * | Joise is a derivative work based on Josua Tippetts' C++ library:
 * | http://accidentalnoise.sourceforge.net/index.html
 * ============================================================================
 * 
 * Copyright (C) 2011 Joshua Tippetts
 * 
 *   This software is provided 'as-is', without any express or implied
 *   warranty.  In no event will the authors be held liable for any damages
 *   arising from the use of this software.
 * 
 *   Permission is granted to anyone to use this software for any purpose,
 *   including commercial applications, and to alter it and redistribute it
 *   freely, subject to the following restrictions:
 * 
 *   1. The origin of this software must not be misrepresented; you must not
 *      claim that you wrote the original software. If you use this software
 *      in a product, an acknowledgment in the product documentation would be
 *      appreciated but is not required.
 *   2. Altered source versions must be plainly marked as such, and must not be
 *      misrepresented as being the original software.
 *   3. This notice may not be removed or altered from any source distribution.
 */

package com.sudoplay.joise.module;

import com.sudoplay.joise.ModuleInstanceMap;
import com.sudoplay.joise.ModuleMap;
import com.sudoplay.joise.ModulePropertyMap;

public class ModuleScaleDomain extends SourcedModule {

  public static final double DEFAULT_SCALE = 1.0;

  protected ScalarParameter sx = new ScalarParameter(1.0);
  protected ScalarParameter sy = new ScalarParameter(1.0);
  protected ScalarParameter sz = new ScalarParameter(1.0);
  protected ScalarParameter sw = new ScalarParameter(1.0);
  protected ScalarParameter su = new ScalarParameter(1.0);
  protected ScalarParameter sv = new ScalarParameter(1.0);

  public void setScaleX(double x) {
    sx.set(x);
  }

  public void setScaleY(double y) {
    sy.set(y);
  }

  public void setScaleZ(double z) {
    sz.set(z);
  }

  public void setScaleW(double w) {
    sw.set(w);
  }

  public void setScaleU(double u) {
    su.set(u);
  }

  public void setScaleV(double v) {
    sv.set(v);
  }

  public void setScaleX(Module x) {
    sx.set(x);
  }

  public void setScaleY(Module y) {
    sy.set(y);
  }

  public void setScaleZ(Module z) {
    sz.set(z);
  }

  public void setScaleW(Module w) {
    sw.set(w);
  }

  public void setScaleU(Module u) {
    su.set(u);
  }

  public void setScaleV(Module v) {
    sv.set(v);
  }

  @Override
  public double get(double x, double y) {
    return source.get(x * sx.get(x, y), y * sy.get(x, y));
  }

  @Override
  public double get(double x, double y, double z) {
    return source.get(x * sx.get(x, y, z), y * sy.get(x, y, z),
        z * sz.get(x, y, z));
  }

  @Override
  public double get(double x, double y, double z, double w) {
    return source.get(x * sx.get(x, y, z, w), y * sy.get(x, y, z, w),
        z * sz.get(x, y, z, w), w * sw.get(x, y, z, w));
  }

  @Override
  public double get(double x, double y, double z, double w, double u, double v) {
    return source.get(x * sx.get(x, y, z, w, u, v),
        y * sy.get(x, y, z, w, u, v), z * sz.get(x, y, z, w, u, v),
        w * sw.get(x, y, z, w, u, v), u * su.get(x, y, z, w, u, v),
        v * sv.get(x, y, z, w, u, v));
  }

  @Override
  protected void _writeToMap(ModuleMap map) {

    ModulePropertyMap props = new ModulePropertyMap(this);

    writeScalar("x", sx, props, map);
    writeScalar("y", sy, props, map);
    writeScalar("z", sz, props, map);
    writeScalar("w", sw, props, map);
    writeScalar("u", su, props, map);
    writeScalar("v", sv, props, map);
    writeSource(props, map);

    map.put(getId(), props);

  }

  @Override
  public Module buildFromPropertyMap(ModulePropertyMap props,
      ModuleInstanceMap map) {

    readScalar("x", "setScaleX", props, map);
    readScalar("y", "setScaleY", props, map);
    readScalar("z", "setScaleZ", props, map);
    readScalar("w", "setScaleW", props, map);
    readScalar("u", "setScaleU", props, map);
    readScalar("v", "setScaleV", props, map);
    readSource(props, map);

    return this;
  }

}
