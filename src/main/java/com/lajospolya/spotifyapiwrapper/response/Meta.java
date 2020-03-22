package com.lajospolya.spotifyapiwrapper.response;

public class Meta
{
    private String analyzer_version;
    private String platform;
    private String detailed_status;
    private String status_code;
    private Long timestamp;
    private Float analysis_time;
    private String input_process;

    public String getAnalyzer_version()
    {
        return analyzer_version;
    }

    public void setAnalyzer_version(String analyzer_version)
    {
        this.analyzer_version = analyzer_version;
    }

    public String getPlatform()
    {
        return platform;
    }

    public void setPlatform(String platform)
    {
        this.platform = platform;
    }

    public String getDetailed_status()
    {
        return detailed_status;
    }

    public void setDetailed_status(String detailed_status)
    {
        this.detailed_status = detailed_status;
    }

    public String getStatus_code()
    {
        return status_code;
    }

    public void setStatus_code(String status_code)
    {
        this.status_code = status_code;
    }

    public Long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Long timestamp)
    {
        this.timestamp = timestamp;
    }

    public Float getAnalysis_time()
    {
        return analysis_time;
    }

    public void setAnalysis_time(Float analysis_time)
    {
        this.analysis_time = analysis_time;
    }

    public String getInput_process()
    {
        return input_process;
    }

    public void setInput_process(String input_process)
    {
        this.input_process = input_process;
    }
}
