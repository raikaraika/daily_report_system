package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.ReactionConverter;
import actions.views.ReactionView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Reaction;

public class ReactionService extends ServiceBase {

	/**
     * 指定された日報の指定されたページ数のいいね一覧画面に表示する分取得しReactionViewのリストで返却する
     * @param report 日報
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<ReactionView> getAllPerPage(ReportView report, int page) {

        List<Reaction> reactions = em.createNamedQuery(JpaConst.Q_REA_GET_ALL, Reaction.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ReactionConverter.toViewList(reactions);
    }

    /**
     * 指定した日報のいいねデータの件数を取得し、返却する
     * @param report 日報
     * @return いいねデータの件数
     */
    public long countAll(ReportView report) {

        long reactions_count = (long) em.createNamedQuery(JpaConst.Q_REA_COUNT, Long.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
                .getSingleResult();

        return reactions_count;
    }

    /**
     * 指定した従業員がいいねしたかを判定
     * @param reaction
     * @param employee
     * @param report
     * @return いいねデータのture/false
     */
    public boolean countMineReaction(EmployeeView employee, ReportView report) {

    	long countMine = (long) em.createNamedQuery(JpaConst.Q_REA_COUNT_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
                .getSingleResult();

        if (countMine == 0) {
            return false;

        } else {
            return true;
        }
    }


	/**
	 * いいねするリンクを押下でデータを1件作成し、いいねテーブルに登録
	 * @param rav いいねの登録内容
	 */
	public void create(ReactionView rav){
			LocalDateTime ldt = LocalDateTime.now();
			rav.setCreatedAt(ldt);
			rav.setUpdatedAt(ldt);
			createInternal(rav);
		}

	/**
     * いいねデータを1件登録する
     * @param rav いいねデータ
     */
    private void createInternal(ReactionView rav) {

        em.getTransaction().begin();
        em.persist(ReactionConverter.toModel(rav));
        em.getTransaction().commit();

    }

}
