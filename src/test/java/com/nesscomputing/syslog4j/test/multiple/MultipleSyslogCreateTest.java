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
package com.nesscomputing.syslog4j.test.multiple;

import static com.nesscomputing.syslog4j.SyslogConstants.USE_STRUCTURED_DATA_DEFAULT;

import java.nio.charset.Charset;

import junit.framework.TestCase;

import com.google.common.base.Charsets;
import com.nesscomputing.syslog4j.Syslog;
import com.nesscomputing.syslog4j.SyslogBackLogHandlerIF;
import com.nesscomputing.syslog4j.SyslogConfigIF;
import com.nesscomputing.syslog4j.SyslogConstants;
import com.nesscomputing.syslog4j.SyslogFacility;
import com.nesscomputing.syslog4j.SyslogIF;
import com.nesscomputing.syslog4j.SyslogLevel;
import com.nesscomputing.syslog4j.SyslogMessageIF;
import com.nesscomputing.syslog4j.SyslogMessageModifierIF;
import com.nesscomputing.syslog4j.SyslogMessageProcessorIF;
import com.nesscomputing.syslog4j.SyslogRuntimeException;
import com.nesscomputing.syslog4j.impl.message.processor.SyslogMessageProcessor;
import com.nesscomputing.syslog4j.impl.message.processor.structured.StructuredSyslogMessageProcessor;
import com.nesscomputing.syslog4j.impl.multiple.MultipleSyslogConfig;

public class MultipleSyslogCreateTest extends TestCase {
    public static class FakeSyslog implements SyslogIF {
        public String protocol = null;
        public SyslogConfigIF config = null;

        public int total = 0;

        public void debug(String message) { this.total += 1; }
        public void debug(SyslogMessageIF message) { this.total += 2; }

        public void info(String message) { this.total += 4; }
        public void info(SyslogMessageIF message) { this.total += 8; }

        public void notice(String message) { this.total += 16; }
        public void notice(SyslogMessageIF message) { this.total += 32; }

        public void warn(String message) { this.total += 64; }
        public void warn(SyslogMessageIF message) { this.total += 128; }

        public void error(String message) { this.total += 256; }
        public void error(SyslogMessageIF message) { this.total += 512; }

        public void critical(String message) { this.total += 1024; }
        public void critical(SyslogMessageIF message) { this.total += 2048; }

        public void alert(String message) { this.total += 4096; }
        public void alert(SyslogMessageIF message) { this.total += 8192; }

        public void emergency(String message) { this.total += 16384; }
        public void emergency(SyslogMessageIF message) { this.total += 32768; }

        public void backLog(SyslogLevel level, String message, Throwable reasonThrowable) { }
        public void backLog(SyslogLevel level, String message, String reason) { }

        public void flush() throws SyslogRuntimeException { }

        public SyslogConfigIF getConfig() { return this.config; }

        public SyslogMessageProcessorIF getMessageProcessor() { return SyslogMessageProcessor.getDefault(); }
        public void setMessageProcessor(SyslogMessageProcessorIF messageProcessor) { }

        public SyslogMessageProcessorIF getStructuredMessageProcessor() { return StructuredSyslogMessageProcessor.getDefault(); }
        public void setStructuredMessageProcessor(SyslogMessageProcessorIF messageProcessor) { }

        public String getProtocol() { return this.protocol; }

        public void initialize(String syslogProtocol, SyslogConfigIF syslogConfig) throws SyslogRuntimeException {
            this.protocol = syslogProtocol;
            this.config = syslogConfig;
        }

        public void log(SyslogLevel level, String message) {
            if (SyslogLevel.DEBUG == level) { debug(message); }
            if (SyslogLevel.INFO == level) { info(message); }
            if (SyslogLevel.NOTICE == level) { notice(message); }
            if (SyslogLevel.WARN == level) { warn(message); }
            if (SyslogLevel.ERROR == level) { error(message); }
            if (SyslogLevel.CRITICAL == level) { critical(message); }
            if (SyslogLevel.ALERT == level) { alert(message); }
            if (SyslogLevel.EMERGENCY == level) { emergency(message); }
        }

        public void log(SyslogLevel level, SyslogMessageIF message) {
            if (SyslogLevel.DEBUG == level) { debug(message); }
            if (SyslogLevel.INFO == level) { info(message); }
            if (SyslogLevel.NOTICE == level) { notice(message); }
            if (SyslogLevel.WARN == level) { warn(message); }
            if (SyslogLevel.ERROR == level) { error(message); }
            if (SyslogLevel.CRITICAL == level) { critical(message); }
            if (SyslogLevel.ALERT == level) { alert(message); }
            if (SyslogLevel.EMERGENCY == level) { emergency(message); }
        }

        public void shutdown() throws SyslogRuntimeException { }
    }

    public static class FakeSyslogConfig implements SyslogConfigIF {
        public void addBackLogHandler(SyslogBackLogHandlerIF backLogHandler) { }

        public void addMessageModifier(SyslogMessageModifierIF messageModifier) { }

        @Override
        public Charset getCharSet() { return Charsets.UTF_8; }

        @Override
        public SyslogFacility getFacility() { return SyslogFacility.DEFAULT; }

        public String getHost() { return SyslogConstants.SYSLOG_HOST_DEFAULT; }

        public String getIdent() { return ""; }

        public String getLocalName() { return null; }

        public int getPort() { return SyslogConstants.SYSLOG_PORT_DEFAULT; }

        public boolean isTruncateMessage() { return SyslogConstants.TRUNCATE_MESSAGE_DEFAULT; }

        public int getMaxMessageLength() { return SyslogConstants.MAX_MESSAGE_LENGTH_DEFAULT; }

        public Class<? extends SyslogIF> getSyslogClass() { return FakeSyslog.class; }

        public void insertBackLogHandler(int index, SyslogBackLogHandlerIF backLogHandler) { }

        public void insertMessageModifier(int index, SyslogMessageModifierIF messageModifier) { }

        public boolean isIncludeIdentInMessageModifier() { return SyslogConstants.INCLUDE_IDENT_IN_MESSAGE_MODIFIER_DEFAULT; }

        public boolean isSendLocalName() { return SyslogConstants.SEND_LOCAL_NAME_DEFAULT; }

        public boolean isSendLocalTimestamp() { return SyslogConstants.SEND_LOCAL_TIMESTAMP_DEFAULT; }

        public boolean isThrowExceptionOnInitialize() { return SyslogConstants.THROW_EXCEPTION_ON_INITIALIZE_DEFAULT; }

        public boolean isThrowExceptionOnWrite() { return SyslogConstants.THROW_EXCEPTION_ON_WRITE_DEFAULT; }

        public void removeAllBackLogHandlers() { }

        public void removeAllMessageModifiers() { }

        public void removeBackLogHandler(SyslogBackLogHandlerIF backLogHandler) { }

        public void removeMessageModifier(SyslogMessageModifierIF messageModifier) { }

        @Override
        public void setCharSet(Charset charSet) { }

        @Override
        public void setFacility(SyslogFacility facility) { }

        public void setHost(String host) throws SyslogRuntimeException { }

        public void setIdent(String ident) { }

        public void setLocalName(String localName) { }

        public void setIncludeIdentInMessageModifier(boolean throwExceptionOnInitialize) { }

        public void setPort(int port) throws SyslogRuntimeException { }

        public void setSendLocalName(boolean sendLocalName) { }

        public void setSendLocalTimestamp(boolean sendLocalTimestamp) { }

        public void setThrowExceptionOnInitialize(boolean throwExceptionOnInitialize) { }

        public void setThrowExceptionOnWrite(boolean throwExceptionOnWrite) { }

        public void setTruncateMessage(boolean truncateMessage) { }

        public void setMaxMessageLength(int maxMessageLength) { }

        public boolean isUseStructuredData() { return USE_STRUCTURED_DATA_DEFAULT; }

        public void setUseStructuredData(boolean useStructuredData) { }
    }

    public static class FakeSyslogMessage implements SyslogMessageIF {
        public String createMessage() {
            return "fake message";
        }

        public String getProcId()
        {
            return null;
        }
    }

    public void testMultipleSyslog() {
        FakeSyslogConfig config1 = new FakeSyslogConfig();
        FakeSyslog fake1 = (FakeSyslog) Syslog.createInstance("fake1", config1);

        FakeSyslogConfig config2 = new FakeSyslogConfig();
        FakeSyslog fake2 = (FakeSyslog) Syslog.createInstance("fake2", config2);

        MultipleSyslogConfig config = new MultipleSyslogConfig();
        config.addProtocol("fake1");
        config.addProtocol("fake2");

        SyslogIF syslog = Syslog.createInstance("multiple",config);

        assertEquals(0,fake1.total);
        assertEquals(0,fake2.total);

        syslog.debug("test");
        assertEquals(1,fake1.total);
        assertEquals(1,fake2.total);

        syslog.debug(new FakeSyslogMessage());
        assertEquals(1 + 2,fake1.total);
        assertEquals(1 + 2,fake2.total);

        syslog.info("test");
        assertEquals(1 + 2 + 4,fake1.total);
        assertEquals(1 + 2 + 4,fake2.total);

        syslog.info(new FakeSyslogMessage());
        assertEquals(1 + 2 + 4 + 8,fake1.total);
        assertEquals(1 + 2 + 4 + 8,fake2.total);

        syslog.notice("test");
        assertEquals(1 + 2 + 4 + 8 + 16,fake1.total);
        assertEquals(1 + 2 + 4 + 8 + 16,fake2.total);

        syslog.notice(new FakeSyslogMessage());
        assertEquals(1 + 2 + 4 + 8 + 16 + 32,fake1.total);
        assertEquals(1 + 2 + 4 + 8 + 16 + 32,fake2.total);

        syslog.warn("test");
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64,fake1.total);
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64,fake2.total);

        syslog.warn(new FakeSyslogMessage());
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128,fake1.total);
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128,fake2.total);

        syslog.error("test");
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256,fake1.total);
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256,fake2.total);

        syslog.error(new FakeSyslogMessage());
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512,fake1.total);
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512,fake2.total);

        syslog.critical("test");
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024,fake1.total);
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024,fake2.total);

        syslog.critical(new FakeSyslogMessage());
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048,fake1.total);
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048,fake2.total);

        syslog.alert("test");
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048 + 4096,fake1.total);
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048 + 4096,fake2.total);

        syslog.alert(new FakeSyslogMessage());
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048 + 4096 + 8192,fake1.total);
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048 + 4096 + 8192,fake2.total);

        syslog.emergency("test");
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048 + 4096 + 8192 + 16384,fake1.total);
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048 + 4096 + 8192 + 16384,fake2.total);

        syslog.emergency(new FakeSyslogMessage());
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048 + 4096 + 8192 + 16384 + 32768,fake1.total);
        assertEquals(1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048 + 4096 + 8192 + 16384 + 32768,fake2.total);
    }
}
