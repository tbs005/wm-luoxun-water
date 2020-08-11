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
public class WhWatersystem extends Model<WhWatersystem> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private String deviceCode;

    private String deviceName;

    private String areaId;

    private BigDecimal locateX;

    private BigDecimal locateY;

    private String systemType;

    /**
     * 水表的状态
     */
    private String state;

    /**
     * 水表的编码
     */
    private String dsnum;

    private BigDecimal pressure;

    private LocalDateTime time;

    private Integer threshold;

    /**
     * 阈值上限
     */
    private Integer upperValue;

    /**
     * 阈值下限
     */
    private Integer lowerValue;

    private String deviceExDes;

    /**
     * 服务范围
     */
    private String comments;

    /**
     * 剩余电量
     */
    private Double dumpEnergy;

    /**
     * 水表开始工作时间
     */
    private LocalDateTime workStartTime;

    private String projectid;

    private Integer waterMapid;

    /**
     * 0:市政消火栓；1：室内消火栓；2：室外消火栓；3：消防水箱；4：喷淋官网
     */
    private Integer hydranttype;

    private String buildingid;

    /**
     * 1:泵前;2:泵后;3:末端
     */
    private Integer position;

    /**
     * 0：G表；1：Z表；2：LG表；3：P表；
     */
    @TableField("waterType")
    private Integer waterType;

    /**
     * 1：正常；2：低压；3：超压；4：放水
     */
    @TableField("pressureState")
    private Integer pressureState;

    /**
     * 信号强度（单位：dbm）
     */
    private String rssi;

    /**
     * 0:V1.0;1:V2.0
     */
    private Integer version;

    /**
     * 插入时间
     */
    @TableField("insertTime")
    private LocalDateTime insertTime;

    /**
     * 最近更新时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;

    /**
     * 水表拆除时间
     */
    private LocalDateTime workStopTime;

    /**
     * 终端本地时间戳Unix时间戳
     */
    private String timestick;

    private Integer userId;

    private String imageName;

    private String brand;

    private Integer isFlag;

    /**
     * 1:未读 2：已读
     */
    private Integer readFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最近修改者
     */
    private String modifyUser;

    /**
     * 创建者
     */
    private String createUser;

    /**
     * 最近修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 更新次数
     */
    private Integer updateCnt;

    /**
     * 删除逻辑
     */
    private String delFlg;

    private String pointType;

    private BigDecimal picScale;

    private String cardNumber;

    /**
     * 0:异常 1：正常
     */
    private Integer isNormal;

    /**
     * 当设备异常，需发送异常信息，0表示未发，1表示已发
     */
    private Integer sendFlag;

    /**
     * 1-在线，2-离线，3-离线已发送
     */
    private Integer isOnline;

    /**
     * 静安model Id
     */
    private String jaModelId;


    public static final String ID = "ID";

    public static final String DEVICE_CODE = "device_code";

    public static final String DEVICE_NAME = "device_name";

    public static final String AREA_ID = "area_id";

    public static final String LOCATE_X = "locate_x";

    public static final String LOCATE_Y = "locate_y";

    public static final String SYSTEM_TYPE = "system_type";

    public static final String STATE = "state";

    public static final String DSNUM = "dsnum";

    public static final String PRESSURE = "pressure";

    public static final String TIME = "time";

    public static final String THRESHOLD = "threshold";

    public static final String UPPER_VALUE = "upper_value";

    public static final String LOWER_VALUE = "lower_value";

    public static final String DEVICE_EX_DES = "device_ex_des";

    public static final String COMMENTS = "comments";

    public static final String DUMP_ENERGY = "dump_energy";

    public static final String WORK_START_TIME = "work_start_time";

    public static final String PROJECTID = "projectid";

    public static final String WATER_MAPID = "water_mapid";

    public static final String HYDRANTTYPE = "hydranttype";

    public static final String BUILDINGID = "buildingid";

    public static final String POSITION = "position";

    public static final String WATERTYPE = "waterType";

    public static final String PRESSURESTATE = "pressureState";

    public static final String RSSI = "rssi";

    public static final String VERSION = "version";

    public static final String INSERTTIME = "insertTime";

    public static final String UPDATETIME = "updateTime";

    public static final String WORK_STOP_TIME = "work_stop_time";

    public static final String TIMESTICK = "timestick";

    public static final String USER_ID = "user_id";

    public static final String IMAGE_NAME = "image_name";

    public static final String BRAND = "brand";

    public static final String IS_FLAG = "is_flag";

    public static final String READ_FLAG = "read_flag";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_USER = "modify_user";

    public static final String CREATE_USER = "create_user";

    public static final String MODIFY_TIME = "modify_time";

    public static final String UPDATE_CNT = "update_cnt";

    public static final String DEL_FLG = "del_flg";

    public static final String POINT_TYPE = "point_type";

    public static final String PIC_SCALE = "pic_scale";

    public static final String CARD_NUMBER = "card_number";

    public static final String IS_NORMAL = "is_normal";

    public static final String SEND_FLAG = "send_flag";

    public static final String IS_ONLINE = "is_online";

    public static final String JA_MODEL_ID = "ja_model_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
