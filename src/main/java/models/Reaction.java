package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * いいねデータのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_REA)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_REA_GET_ALL,
            query = JpaConst.Q_REA_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_REA_COUNT,
            query = JpaConst.Q_REA_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_REA_COUNT_MINE,
            query = JpaConst.Q_REA_COUNT_MINE_DEF),
    //@NamedQuery(
            //name = JpaConst.Q_REA_COUNT_ALL_MINE,
            //query = JpaConst.Q_REA_COUNT_ALL_MINE_DEF)
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity

public class Reaction {

	/**
     * id
     */
    @Id
    @Column(name = JpaConst.REA_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * いいねした従業員のID
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.REA_COL_EMP, nullable = false)
    private Employee employee;

    /**
     * いいねされた日報ID
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.REA_COL_REP, nullable = false)
    private Report report;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.REA_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.REA_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

}
