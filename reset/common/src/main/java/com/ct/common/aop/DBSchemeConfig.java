package com.ct.common.aop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The type Db scheme config.
 *
 * @author chen.cheng
 */
@Configuration
@ConfigurationProperties(prefix = "database.schema")
public class DBSchemeConfig {

    /**
     * The Signal.
     *
     * @author chen.cheng
     */
    private String signal;

    /**
     * The Rdnet.
     *
     * @author chen.cheng
     */
    private String rdnet;

    /**
     * The Dwd.
     *
     * @author chen.cheng
     */
    private String dwd;

    /**
     * The Screen.
     *
     * @author chen.cheng
     */
    private String screen;

    /**
     * The Model.
     *
     * @author chen.cheng
     */
    private String model;

    /**
     * The Event handle.
     *
     * @author chen.cheng
     */
    private String eventHandle;

    /**
     * The Dws m.
     *
     * @author chen.cheng
     */
    private String dwsM;

    /**
     * Gets signal.
     *
     * @return the signal
     * @author chen.cheng
     */
    public String getSignal() {
        return signal;
    }

    /**
     * Sets signal.
     *
     * @param signal the signal
     * @author chen.cheng
     */
    public void setSignal(String signal) {
        this.signal = signal;
    }

    /**
     * Gets rdnet.
     *
     * @return the rdnet
     * @author chen.cheng
     */
    public String getRdnet() {
        return rdnet;
    }

    /**
     * Sets rdnet.
     *
     * @param rdnet the rdnet
     * @author chen.cheng
     */
    public void setRdnet(String rdnet) {
        this.rdnet = rdnet;
    }

    /**
     * Gets dwd.
     *
     * @return the dwd
     * @author chen.cheng
     */
    public String getDwd() {
        return dwd;
    }

    /**
     * Sets dwd.
     *
     * @param dwd the dwd
     * @author chen.cheng
     */
    public void setDwd(String dwd) {
        this.dwd = dwd;
    }

    /**
     * Gets screen.
     *
     * @return the screen
     * @author chen.cheng
     */
    public String getScreen() {
        return screen;
    }

    /**
     * Sets screen.
     *
     * @param screen the screen
     * @author chen.cheng
     */
    public void setScreen(String screen) {
        this.screen = screen;
    }

    /**
     * Gets model.
     *
     * @return the model
     * @author chen.cheng
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets model.
     *
     * @param model the model
     * @author chen.cheng
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets event handle.
     *
     * @return the event handle
     * @author chen.cheng
     */
    public String getEventHandle() {
        return eventHandle;
    }

    /**
     * Sets event handle.
     *
     * @param eventHandle the event handle
     * @author chen.cheng
     */
    public void setEventHandle(String eventHandle) {
        this.eventHandle = eventHandle;
    }

    /**
     * Gets dws m.
     *
     * @return the dws m
     * @author chen.cheng
     */
    public String getDwsM() {
        return dwsM;
    }

    /**
     * Sets dws m.
     *
     * @param dwsM the dws m
     * @author chen.cheng
     */
    public void setDwsM(String dwsM) {
        this.dwsM = dwsM;
    }
}
