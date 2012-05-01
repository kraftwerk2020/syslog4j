/**
 *
 * (C) Copyright 2008-2011 syslog4j.org
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
package com.nesscomputing.syslog4j.impl.message.modifier.hash;

import com.nesscomputing.syslog4j.impl.message.modifier.AbstractSyslogMessageModifierConfig;

/**
* HashSyslogMessageModifierConfig is an implementation of AbstractSyslogMessageModifierConfig
* that provides configuration for HashSyslogMessageModifier.
*
* <p>Syslog4j is licensed under the Lesser GNU Public License v2.1.  A copy
* of the LGPL license is available in the META-INF folder in all
* distributions of Syslog4j and in the base directory of the "doc" ZIP.</p>
*
* @author &lt;syslog4j@productivity.org&gt;
* @version $Id: HashSyslogMessageModifierConfig.java,v 1.1 2008/11/10 04:38:37 cvs Exp $
*/
public class HashSyslogMessageModifierConfig extends AbstractSyslogMessageModifierConfig {
    protected String hashAlgorithm = null;

    public static final HashSyslogMessageModifierConfig createMD5() {
        HashSyslogMessageModifierConfig md5 = new HashSyslogMessageModifierConfig("MD5");

        return md5;
    }

    public static final HashSyslogMessageModifierConfig createSHA1() {
        HashSyslogMessageModifierConfig sha1 = new HashSyslogMessageModifierConfig("SHA1");

        return sha1;
    }

    public static final HashSyslogMessageModifierConfig createSHA160() {
         return createSHA1();
    }

    public static final HashSyslogMessageModifierConfig createSHA256() {
        HashSyslogMessageModifierConfig sha256 = new HashSyslogMessageModifierConfig("SHA-256");

        return sha256;
    }

    public static final HashSyslogMessageModifierConfig createSHA384() {
        HashSyslogMessageModifierConfig sha384 = new HashSyslogMessageModifierConfig("SHA-384");

        return sha384;
    }

    public static final HashSyslogMessageModifierConfig createSHA512() {
        HashSyslogMessageModifierConfig sha512 = new HashSyslogMessageModifierConfig("SHA-512");

        return sha512;
    }

    public HashSyslogMessageModifierConfig(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public String getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }
}