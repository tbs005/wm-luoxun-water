package com.waimung.luoxun.water.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wwh
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WhWaterstatistics extends Model<WhWaterstatistics> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 水表地址
     */
    private String dsnum;

    private BigDecimal pressure;

    private LocalDateTime time;

    /**
     * 心跳类型：0：普通心跳类型；1：事件触发类型
     */
    @TableField("eventType")
    private Integer eventType;

    /**
     * 1：正常；2：低压；3：超压；4：放水
     */
    private Integer state;

    /**
     * 信号强度（单位：dbm）
     */
    private String rssi;

    /**
     * 剩余电量( %)
     */
    private String dumpEnergy;

    /**
     * 传输帧编号
     */
    private Integer packetid;

    /**
     * 紧急事件编号
     */
    private Integer eventid;

    @TableField("serverTime")
    private LocalDateTime serverTime;

    private String extraProperty;


    public static final String ID = "ID";

    public static final String DSNUM = "dsnum";

    public static final String PRESSURE = "pressure";

    public static final String TIME = "time";

    public static final String EVENTTYPE = "eventType";

    public static final String STATE = "state";

    public static final String RSSI = "rssi";

    public static final String DUMP_ENERGY = "dump_energy";

    public static final String PACKETID = "packetid";

    public static final String EVENTID = "eventid";

    public static final String SERVERTIME = "serverTime";

    public static final String EXTRA_PROPERTY = "extra_property";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
