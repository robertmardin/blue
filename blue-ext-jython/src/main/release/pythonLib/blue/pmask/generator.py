# generator.py

# PMask, a Python implementation of CMask
# Copyright (C) 2000 by Maurizio Umberto Puxeddu
# Copyright (C) 2012 by Steven Yi

# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; either version 2 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

import types

from pmask.exception import *

class Generator:
    def __init__(self):
        pass
    
    def valueAt(self, evaluationTime):
        raise AbstractBaseClass

def evaluateAt(object, evaluationTime):
    if type(object) == types.FloatType:
        return object
    elif not type(object) == types.InstanceType:
        return float(object)
    else:
        return object.valueAt(evaluationTime)

class FuncGen(Generator):
  """Generator that acceptors one generator and one single-arg function. Evaluation will 
  call the passed-in generator, then apply the function to the generator. Useful with
  lambdas and partials for the passed in function."""
  def __init__(self, gen, func):
    Generator.__init__(self)
    self.gen = gen
    self.func = func

  def valueAt(self,t):
    return self.func(self.gen.valueAt(t))

class CombiGen(Generator):
  """Generator that acceptors two generators and one two-arg function. Evaluation will 
  call valueAt on the two generators, then pass the results to the two-arg function for
  further processing. Useful with lambdas and partials for the passed in function."""
  def __init__(self, gen1, gen2, func):
    Generator.__init__(self)
    self.gen1 = gen1
    self.gen2 = gen2
    self.func = func

  def valueAt(self,t):
    return func(self.gen1.valueAt(t), self.gen2.valueAt(t))
