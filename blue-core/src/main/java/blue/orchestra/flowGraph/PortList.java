/*
 * blue - object composition environment for csound
 * Copyright (c) 2000-2005 Steven Yi (stevenyi@gmail.com)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by  the Free Software Foundation; either version 2 of the License or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; see the file COPYING.LIB.  If not, write to
 * the Free Software Foundation Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307 USA
 */
package blue.orchestra.flowGraph;

import java.util.ArrayList;

public class PortList {
    private ArrayList ports = new ArrayList();

    public boolean addPort(Port p) {
        return ports.add(p);
    }

    public boolean remove(Port p) {
        return ports.remove(p);
    }

    public Port getPort(int index) {
        return (Port) ports.get(index);
    }

    public int size() {
        return ports.size();
    }

}
