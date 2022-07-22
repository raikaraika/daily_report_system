package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Reaction;


/**
 * いいねデータのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */

public class ReactionConverter {
	 /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rv ReactionViewのインスタンス
     * @return Reactionのインスタンス
     */
    public static Reaction toModel(ReactionView rav) {
        return new Reaction(
        		rav.getId(),
                EmployeeConverter.toModel(rav.getEmployee()),
                ReportConverter.toModel(rav.getReport()),
                rav.getCreatedAt(),
                rav.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r Reactionのインスタンス
     * @return ReactionViewのインスタンス
     */
    public static ReactionView toView(Reaction ra) {

        if (ra == null) {
            return null;
        }

        return new ReactionView(
                ra.getId(),
                EmployeeConverter.toView(ra.getEmployee()),
                ReportConverter.toView(ra.getReport()),
                ra.getCreatedAt(),
                ra.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<ReactionView> toViewList(List<Reaction> list) {
        List<ReactionView> vs = new ArrayList<>();

        for (Reaction ra : list) {
            vs.add(toView(ra));
        }

        return vs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Reaction ra, ReactionView rav) {
        ra.setId(rav.getId());
        ra.setEmployee(EmployeeConverter.toModel(rav.getEmployee()));
        ra.setReport(ReportConverter.toModel(rav.getReport()));
        ra.setCreatedAt(rav.getCreatedAt());
        ra.setUpdatedAt(rav.getUpdatedAt());

    }

}
