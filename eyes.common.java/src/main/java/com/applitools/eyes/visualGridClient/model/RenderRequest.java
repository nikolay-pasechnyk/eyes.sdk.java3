package com.applitools.eyes.visualGridClient.model;

import com.applitools.eyes.visualGridClient.services.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class  RenderRequest {
    @JsonInclude
    private String renderId;

    @JsonIgnore
    private final Task task;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String webhook;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String url;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RGridDom dom;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, RGridResource> resources;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RenderInfo renderInfo;

    @JsonIgnore
    private String platform;

    @JsonIgnore
    private String browserName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object scriptHooks;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] selectorsToFindRegionsFor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean sendDom;

    public RenderRequest(String webHook, String url, RGridDom dom, Map<String, RGridResource> resources, RenderInfo renderInfo, String platform, String browserName, Object scriptHooks, String[] selectorsToFindRegionsFor, boolean sendDom, Task task) {
        this.webhook = webHook;
        this.url = url;
        this.dom = dom;
        this.resources = resources;
        this.renderInfo = renderInfo;
        this.platform = platform;
        this.browserName = browserName;
        this.scriptHooks = scriptHooks;
        this.selectorsToFindRegionsFor = selectorsToFindRegionsFor;
        this.sendDom = sendDom;
        this.task = task;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RGridDom getDom() {
        return dom;
    }

    public void setDom(RGridDom dom) {
        this.dom = dom;
    }

    public Map<String, RGridResource> getResources() {
        return resources;
    }

    public void setResources(Map<String, RGridResource> resources) {
        this.resources = resources;
    }

    public RenderInfo getRenderInfo() {
        return renderInfo;
    }

    public void setRenderInfo(RenderInfo renderInfo) {
        this.renderInfo = renderInfo;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public Object getScriptHooks() {
        return scriptHooks;
    }

    public void setScriptHooks(Object scriptHooks) {
        this.scriptHooks = scriptHooks;
    }

    public String[] getSelectorsToFindRegionsFor() {
        return selectorsToFindRegionsFor;
    }

    public void setSelectorsToFindRegionsFor(String[] selectorsToFindRegionsFor) {
        this.selectorsToFindRegionsFor = selectorsToFindRegionsFor;
    }

    public boolean isSendDom() {
        return sendDom;
    }

    public void setSendDom(boolean sendDom) {
        this.sendDom = sendDom;
    }

    @JsonProperty("browser")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Map<String,String> getBrowser(){
        Map<String,String> map = new HashMap<>();
        map.put("name", this.browserName);
        map.put("platform", this.platform);
        return map;
    }

    public Task getTask() {
        return task;
    }

    public String getWebhook() {
        return webhook;
    }

    public String getRenderId() {
        return renderId;
    }

    public void setRenderId(String renderId) {
        this.renderId = renderId;
    }
}
