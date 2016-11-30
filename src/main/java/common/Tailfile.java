package common;

import scala.Int;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/18
 * Time: 19:58
 * To change this template use File | Settings | File Templates.
 */

public class Tailfile  {

    private Long id;
    private String hostname;
    private Long count;
    private String tagName;
    private String fileName;
    private Long inode ;
    private Integer nodeType;

    private Integer type;
    private Long timeStamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getInode() {
        return inode;
    }

    public void setInode(Long inode) {
        this.inode = inode;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "Tailfile{" +
                "id=" + id +
                ", hostname='" + hostname + '\'' +
                ", count=" + count +
                ", tagName='" + tagName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", inode=" + inode +
                ", nodeType=" + nodeType +
                ", type=" + type +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
