package com.data.fix;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.CharSink;
import com.google.common.io.Files;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;


public class FileReplace {
    public static List<String> files = new ArrayList<>();
    static {
        files.add("/config/patent.js");
        files.add("/config/authorized.js");
        files.add("/config/Molecule.js");
        files.add("/config/allModule.js");
        files.add("/views/Home/index.scss");
        files.add("/views/Home/index.jsx");
        files.add("/views/Terms/Privacy.jsx");
        files.add("/views/Terms/index.scss");
        files.add("/views/Terms/Protocal.jsx");
        files.add("/views/Terms/index.jsx");
        files.add("/views/Patent/index.scss");
        files.add("/views/Patent/Mark/index.scss");
        files.add("/views/Patent/Mark/types.js");
        files.add("/views/Patent/Mark/reducer.js");
        files.add("/views/Patent/Mark/actions.js");
        files.add("/views/Patent/Mark/PageMark.jsx");
        files.add("/views/Patent/Mark/comp/ListHeader/index.scss");
        files.add("/views/Patent/Mark/comp/ListHeader/index.jsx");
        files.add("/views/Patent/Mark/comp/PDFHeader/index.scss");
        files.add("/views/Patent/Mark/comp/PDFHeader/index.jsx");
        files.add("/views/Patent/Mark/comp/MolEditModal/PositionBox.jsx");
        files.add("/views/Patent/Mark/comp/MolEditModal/index.scss");
        files.add("/views/Patent/Mark/comp/MolEditModal/index.jsx");
        files.add("/views/Patent/Mark/comp/MolEditModal/KetcherBox.jsx");
        files.add("/views/Patent/Mark/comp/PDF/index.scss");
        files.add("/views/Patent/Mark/comp/PDF/index.jsx");
        files.add("/views/Patent/Mark/comp/PatternInfo/index.scss");
        files.add("/views/Patent/Mark/comp/PatternInfo/index.jsx");
        files.add("/views/Patent/Mark/comp/List/index.scss");
        files.add("/views/Patent/Mark/comp/List/index.jsx");
        files.add("/views/Patent/Mark/comp/FiterBar/Upload.jsx");
        files.add("/views/Patent/Mark/comp/FiterBar/index.scss");
        files.add("/views/Patent/Mark/comp/FiterBar/index.jsx");
        files.add("/views/Patent/Mark/comp/ListTable/index.scss");
        files.add("/views/Patent/Mark/comp/ListTable/comp/ExpandPanel/index.scss");
        files.add("/views/Patent/Mark/comp/ListTable/comp/ExpandPanel/comp/ChemicalStructure/PostionTable.jsx");
        files.add("/views/Patent/Mark/comp/ListTable/comp/ExpandPanel/comp/ChemicalStructure/RightPos.jsx");
        files.add("/views/Patent/Mark/comp/ListTable/comp/ExpandPanel/comp/ChemicalStructure/index.scss");
        files.add("/views/Patent/Mark/comp/ListTable/comp/ExpandPanel/comp/ChemicalStructure/StructBox.jsx");
        files.add("/views/Patent/Mark/comp/ListTable/comp/ExpandPanel/comp/ChemicalStructure/index.jsx");
        files.add("/views/Patent/Mark/comp/ListTable/comp/ExpandPanel/index.jsx");
        files.add("/views/Patent/Mark/comp/ListTable/comp/ColNo/index.scss");
        files.add("/views/Patent/Mark/comp/ListTable/comp/ColNo/index.jsx");
        files.add("/views/Patent/Mark/comp/ListTable/comp/ColAction/index.scss");
        files.add("/views/Patent/Mark/comp/ListTable/comp/ColAction/index.jsx");
        files.add("/views/Patent/Mark/comp/ListTable/index.jsx");
        files.add("/views/Patent/Mark/comp/StatTable/index.scss");
        files.add("/views/Patent/Mark/comp/StatTable/index.jsx");
        files.add("/views/Patent/Mark/comp/Editor/index.scss");
        files.add("/views/Patent/Mark/comp/Editor/index.jsx");
        files.add("/views/Patent/comp/PatternModal/index.scss");
        files.add("/views/Patent/comp/PatternModal/Name.jsx");
        files.add("/views/Patent/comp/PatternModal/index.jsx");
        files.add("/views/Patent/comp/PatternModal/Nums.jsx");
        files.add("/views/Patent/reducers.js");
        files.add("/views/Patent/ProjectList/index.scss");
        files.add("/views/Patent/ProjectList/types.js");
        files.add("/views/Patent/ProjectList/reducer.js");
        files.add("/views/Patent/ProjectList/actions.js");
        files.add("/views/Patent/ProjectList/comp/Card/index.scss");
        files.add("/views/Patent/ProjectList/comp/Card/index.jsx");
        files.add("/views/Patent/ProjectList/comp/EditModal/index.jsx");
        files.add("/views/Patent/ProjectList/comp/CreateModal/index.jsx");
        files.add("/views/Patent/ProjectList/comp/FiterBar/index.scss");
        files.add("/views/Patent/ProjectList/comp/FiterBar/index.jsx");
        files.add("/views/Patent/ProjectList/comp/CardList/index.scss");
        files.add("/views/Patent/ProjectList/comp/CardList/index.jsx");
        files.add("/views/Patent/ProjectList/index.jsx");
        files.add("/views/Patent/ProjectDetail/index.scss");
        files.add("/views/Patent/ProjectDetail/types.js");
        files.add("/views/Patent/ProjectDetail/reducer.js");
        files.add("/views/Patent/ProjectDetail/actions.js");
        files.add("/views/Patent/ProjectDetail/comp/RecordModal/index.scss");
        files.add("/views/Patent/ProjectDetail/comp/RecordModal/index.jsx");
        files.add("/views/Patent/ProjectDetail/comp/SearchListBatched/index.scss");
        files.add("/views/Patent/ProjectDetail/comp/SearchListBatched/index.jsx");
        files.add("/views/Patent/ProjectDetail/comp/SearchListHeader/index.scss");
        files.add("/views/Patent/ProjectDetail/comp/SearchListHeader/index.jsx");
        files.add("/views/Patent/ProjectDetail/comp/MemberModal/index.scss");
        files.add("/views/Patent/ProjectDetail/comp/MemberModal/index.jsx");
        files.add("/views/Patent/ProjectDetail/comp/SearchList/index.scss");
        files.add("/views/Patent/ProjectDetail/comp/SearchList/index.jsx");
        files.add("/views/Patent/ProjectDetail/comp/Table/index.scss");
        files.add("/views/Patent/ProjectDetail/comp/Table/TableMarking.jsx");
        files.add("/views/Patent/ProjectDetail/comp/Table/TaskTable.jsx");
        files.add("/views/Patent/ProjectDetail/comp/Table/TableAuditing.jsx");
        files.add("/views/Patent/ProjectDetail/comp/Table/TableAudited.jsx");
        files.add("/views/Patent/ProjectDetail/comp/Table/TableDone.jsx");
        files.add("/views/Patent/ProjectDetail/comp/Table/TablePassed.jsx");
        files.add("/views/Patent/ProjectDetail/comp/Table/TableDistribution.jsx");
        files.add("/views/Patent/ProjectDetail/comp/CreateModal/index.scss");
        files.add("/views/Patent/ProjectDetail/comp/CreateModal/index2.jsx");
        files.add("/views/Patent/ProjectDetail/comp/CreateModal/comp/Tabs/index.scss");
        files.add("/views/Patent/ProjectDetail/comp/CreateModal/comp/Tabs/index.jsx");
        files.add("/views/Patent/ProjectDetail/comp/CreateModal/comp/Form/Upload.jsx");
        files.add("/views/Patent/ProjectDetail/comp/CreateModal/comp/Form/index.scss");
        files.add("/views/Patent/ProjectDetail/comp/CreateModal/comp/Form/index.jsx");
        files.add("/views/Patent/ProjectDetail/comp/CreateModal/index.jsx");
        files.add("/views/Patent/ProjectDetail/comp/FiterBar/index.scss");
        files.add("/views/Patent/ProjectDetail/comp/FiterBar/index.jsx");
        files.add("/views/Patent/ProjectDetail/comp/Operator/index.scss");
        files.add("/views/Patent/ProjectDetail/comp/Operator/Modal.jsx");
        files.add("/views/Patent/ProjectDetail/comp/Operator/index.jsx");
        files.add("/views/Patent/ProjectDetail/index.jsx");
        files.add("/views/Patent/index.jsx");
        files.add("/views/Patent/PatentViewer/index.scss");
        files.add("/views/Patent/PatentViewer/types.js");
        files.add("/views/Patent/PatentViewer/reducer.js");
        files.add("/views/Patent/PatentViewer/actions.js");
        files.add("/views/Patent/PatentViewer/comp/EditInfo/index.scss");
        files.add("/views/Patent/PatentViewer/comp/EditInfo/index.jsx");
        files.add("/views/Patent/PatentViewer/comp/PDFViewer/index.scss");
        files.add("/views/Patent/PatentViewer/comp/PDFViewer/index.jsx");
        files.add("/views/Patent/PatentViewer/comp/FiterBar/index.scss");
        files.add("/views/Patent/PatentViewer/comp/FiterBar/index.jsx");
        files.add("/views/Patent/PatentViewer/index.jsx");
        files.add("/views/ForgotPass/index.scss");
        files.add("/views/ForgotPass/action.js");
        files.add("/views/ForgotPass/types.js");
        files.add("/views/ForgotPass/reducer.js");
        files.add("/views/ForgotPass/comp/Tabs/index.scss");
        files.add("/views/ForgotPass/comp/Tabs/index.jsx");
        files.add("/views/ForgotPass/comp/Form/index.scss");
        files.add("/views/ForgotPass/comp/Form/index.jsx");
        files.add("/views/ForgotPass/comp/Form/Mail.jsx");
        files.add("/views/ForgotPass/comp/NextStepButton/index.jsx");
        files.add("/views/ForgotPass/comp/StepTwo/index.scss");
        files.add("/views/ForgotPass/comp/StepTwo/index.jsx");
        files.add("/views/ForgotPass/comp/StepOne/index.scss");
        files.add("/views/ForgotPass/comp/StepOne/index.jsx");
        files.add("/views/ForgotPass/comp/NextStep/index.scss");
        files.add("/views/ForgotPass/comp/NextStep/index.jsx");
        files.add("/views/ForgotPass/index.jsx");
        files.add("/views/Knowledge/index.scss");
        files.add("/views/Knowledge/Explorer/index.scss");
        files.add("/views/Knowledge/Explorer/types.js");
        files.add("/views/Knowledge/Explorer/reducer.js");
        files.add("/views/Knowledge/Explorer/actions.js");
        files.add("/views/Knowledge/Explorer/comp/NoResult/index.scss");
        files.add("/views/Knowledge/Explorer/comp/NoResult/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/SideFilter/index.scss");
        files.add("/views/Knowledge/Explorer/comp/SideFilter/comp/SliderFilter/index.scss");
        files.add("/views/Knowledge/Explorer/comp/SideFilter/comp/SliderFilter/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/SideFilter/comp/BookFilter/index.scss");
        files.add("/views/Knowledge/Explorer/comp/SideFilter/comp/BookFilter/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/SideFilter/comp/LevelFilter/index.scss");
        files.add("/views/Knowledge/Explorer/comp/SideFilter/comp/LevelFilter/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/SideFilter/comp/Collapse/index.scss");
        files.add("/views/Knowledge/Explorer/comp/SideFilter/comp/Collapse/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/SideFilter/comp/InfluenceFilter/index.scss");
        files.add("/views/Knowledge/Explorer/comp/SideFilter/comp/InfluenceFilter/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/SideFilter/comp/header/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/SideFilter/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/BookModal/index.scss");
        files.add("/views/Knowledge/Explorer/comp/BookModal/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/Button/index.scss");
        files.add("/views/Knowledge/Explorer/comp/Button/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/Navigator/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/FiterBar/index.scss");
        files.add("/views/Knowledge/Explorer/comp/FiterBar/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/D3Graph/index.scss");
        files.add("/views/Knowledge/Explorer/comp/D3Graph/comp/AutoComplete/index.scss");
        files.add("/views/Knowledge/Explorer/comp/D3Graph/comp/AutoComplete/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/D3Graph/comp/ColorScale/index.scss");
        files.add("/views/Knowledge/Explorer/comp/D3Graph/comp/ColorScale/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/D3Graph/comp/header/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/D3Graph/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/WordCloud/index.scss");
        files.add("/views/Knowledge/Explorer/comp/WordCloud/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/index.scss");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/comp/NodeViewer/index.scss");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/comp/NodeViewer/RelatedList.jsx");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/comp/NodeViewer/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/comp/SortMenu/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/comp/ListItem/WordItem.jsx");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/comp/ListItem/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/comp/EdgeViewer/index.scss");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/comp/EdgeViewer/comp/Category/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/comp/EdgeViewer/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/comp/MainList/index.scss");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/comp/MainList/Edge.jsx");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/comp/MainList/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/comp/SearchBox/index.scss");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/comp/SearchBox/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/RightDrawer/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/FeedbackModal/index.scss");
        files.add("/views/Knowledge/Explorer/comp/FeedbackModal/index.jsx");
        files.add("/views/Knowledge/Explorer/comp/SearchBox/index.scss");
        files.add("/views/Knowledge/Explorer/comp/SearchBox/index.jsx");
        files.add("/views/Knowledge/Explorer/Main.jsx");
        files.add("/views/Knowledge/Explorer/index.jsx");
        files.add("/views/Knowledge/reducers.js");
        files.add("/views/Knowledge/index.jsx");
        files.add("/views/UserSpace/index.scss");
        files.add("/views/UserSpace/action.js");
        files.add("/views/UserSpace/types.js");
        files.add("/views/UserSpace/reducer.js");
        files.add("/views/UserSpace/comp/BindingModal/index.scss");
        files.add("/views/UserSpace/comp/BindingModal/Mobile.jsx");
        files.add("/views/UserSpace/comp/BindingModal/index.jsx");
        files.add("/views/UserSpace/comp/VerifyView/index.scss");
        files.add("/views/UserSpace/comp/VerifyView/index.jsx");
        files.add("/views/UserSpace/comp/Contact/index.scss");
        files.add("/views/UserSpace/comp/Contact/index.jsx");
        files.add("/views/UserSpace/comp/Info/index.scss");
        files.add("/views/UserSpace/comp/Info/index.jsx");
        files.add("/views/UserSpace/comp/Table/index.scss");
        files.add("/views/UserSpace/comp/Table/index.jsx");
        files.add("/views/UserSpace/comp/UserInfo/index.scss");
        files.add("/views/UserSpace/comp/UserInfo/config.js");
        files.add("/views/UserSpace/comp/UserInfo/Edit/index.scss");
        files.add("/views/UserSpace/comp/UserInfo/Edit/index.jsx");
        files.add("/views/UserSpace/comp/UserInfo/Viewer/index.scss");
        files.add("/views/UserSpace/comp/UserInfo/Viewer/index.jsx");
        files.add("/views/UserSpace/comp/UserInfo/index.jsx");
        files.add("/views/UserSpace/comp/LeftSide/index.scss");
        files.add("/views/UserSpace/comp/LeftSide/index.jsx");
        files.add("/views/UserSpace/index.jsx");
        files.add("/views/Molecule/MoleculeList/index.scss");
        files.add("/views/Molecule/MoleculeList/types.js");
        files.add("/views/Molecule/MoleculeList/reducer.js");
        files.add("/views/Molecule/MoleculeList/actions.js");
        files.add("/views/Molecule/MoleculeList/MoleculeDocking.jsx");
        files.add("/views/Molecule/MoleculeList/SkeletonDerivation.jsx");
        files.add("/views/Molecule/MoleculeList/comp/TaskList/index.jsx");
        files.add("/views/Molecule/MoleculeList/comp/FiterBar/index.scss");
        files.add("/views/Molecule/MoleculeList/comp/FiterBar/index.jsx");
        files.add("/views/Molecule/MoleculeList/comp/RemoveButton/index.jsx");
        files.add("/views/Molecule/MoleculeList/MoleculePredict.jsx");
        files.add("/views/Molecule/MoleculeList/ScaffoldHopping.jsx");
        files.add("/views/Molecule/MoleculeList/MoleculeOptimize.jsx");
        files.add("/views/Molecule/MoleculeDetail/MoleculeData.jsx");
        files.add("/views/Molecule/MoleculeDetail/index.scss");
        files.add("/views/Molecule/MoleculeDetail/types.js");
        files.add("/views/Molecule/MoleculeDetail/reducer.js");
        files.add("/views/Molecule/MoleculeDetail/actions.js");
        files.add("/views/Molecule/MoleculeDetail/MoleculeDocking.jsx");
        files.add("/views/Molecule/MoleculeDetail/SkeletonDerivation.jsx");
        files.add("/views/Molecule/MoleculeDetail/comp/TaskParams/index.scss");
        files.add("/views/Molecule/MoleculeDetail/comp/TaskParams/index.jsx");
        files.add("/views/Molecule/MoleculeDetail/comp/SourceModal/index.scss");
        files.add("/views/Molecule/MoleculeDetail/comp/SourceModal/index.jsx");
        files.add("/views/Molecule/MoleculeDetail/comp/DockingModal/index.jsx");
        files.add("/views/Molecule/MoleculeDetail/comp/UploadModal/Upload.jsx");
        files.add("/views/Molecule/MoleculeDetail/comp/UploadModal/index.scss");
        files.add("/views/Molecule/MoleculeDetail/comp/UploadModal/index.jsx");
        files.add("/views/Molecule/MoleculeDetail/comp/LeftCollectFile/index.scss");
        files.add("/views/Molecule/MoleculeDetail/comp/LeftCollectFile/index.jsx");
        files.add("/views/Molecule/MoleculeDetail/comp/ListShow/index.scss");
        files.add("/views/Molecule/MoleculeDetail/comp/ListShow/index.jsx");
        files.add("/views/Molecule/MoleculeDetail/comp/TaskResult/index.scss");
        files.add("/views/Molecule/MoleculeDetail/comp/TaskResult/index.jsx");
        files.add("/views/Molecule/MoleculeDetail/comp/CardAction/index.scss");
        files.add("/views/Molecule/MoleculeDetail/comp/CardAction/index.jsx");
        files.add("/views/Molecule/MoleculeDetail/comp/OperateAction/index.scss");
        files.add("/views/Molecule/MoleculeDetail/comp/OperateAction/index.jsx");
        files.add("/views/Molecule/MoleculeDetail/comp/LeftTabs/index.scss");
        files.add("/views/Molecule/MoleculeDetail/comp/LeftTabs/index.jsx");
        files.add("/views/Molecule/MoleculeDetail/comp/ConditionFilter/index.scss");
        files.add("/views/Molecule/MoleculeDetail/comp/ConditionFilter/filterConfig.js");
        files.add("/views/Molecule/MoleculeDetail/comp/ConditionFilter/index.jsx");
        files.add("/views/Molecule/MoleculeDetail/MoleculePredict.jsx");
        files.add("/views/Molecule/MoleculeDetail/ScaffoldHopping.jsx");
        files.add("/views/Molecule/MoleculeDetail/MoleculeOptimize.jsx");
        files.add("/views/Molecule/MoleculeAdd/index.scss");
        files.add("/views/Molecule/MoleculeAdd/types.js");
        files.add("/views/Molecule/MoleculeAdd/reducer.js");
        files.add("/views/Molecule/MoleculeAdd/actions.js");
        files.add("/views/Molecule/MoleculeAdd/MoleculeDocking.jsx");
        files.add("/views/Molecule/MoleculeAdd/SkeletonDerivation.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/InputLigand/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/InputLigand/comp/InputPanel.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/InputLigand/comp/Bottom.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/InputLigand/comp/TabRadio.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/InputLigand/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/InputMolecule/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/InputMolecule/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/InputPDB/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/InputPDB/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/ThreeDimension/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/ThreeDimension/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/InputName/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/InputName/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/OptimizeSelect/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/OptimizeSelect/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/UploadHolder/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/UploadHolder/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/BigTitle/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/BigTitle/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/SelectModel/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/SelectModel/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/OperateAction/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/OperateAction/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/PlaceHolder/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/PlaceHolder/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/LigandModal/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/LigandModal/comp/MoleculeBox/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/LigandModal/comp/MoleculeBox/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/LigandModal/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/BottomBar/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/BottomBar/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/AdvanceConfigure/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/AdvanceConfigure/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/Upload/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/SmallTitle/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/SmallTitle/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/FixedPoint/index.scss");
        files.add("/views/Molecule/MoleculeAdd/comp/FixedPoint/Tip2.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/FixedPoint/Tip.jsx");
        files.add("/views/Molecule/MoleculeAdd/comp/FixedPoint/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/MoleculePredict.jsx");
        files.add("/views/Molecule/MoleculeAdd/index.jsx");
        files.add("/views/Molecule/MoleculeAdd/ScaffoldHopping.jsx");
        files.add("/views/Molecule/MoleculeAdd/MoleculeOptimize.jsx");
        files.add("/views/Molecule/MoleculeAdd/BaseLayout.jsx");
        files.add("/views/Login/index.scss");
        files.add("/views/Login/action.js");
        files.add("/views/Login/types.js");
        files.add("/views/Login/reducer.js");
        files.add("/views/Login/SignUp.jsx");
        files.add("/views/Login/index2.jsx");
        files.add("/views/Login/comp/Form/index.scss");
        files.add("/views/Login/comp/Form/index.jsx");
        files.add("/views/Login/comp/LoginForm/index.scss");
        files.add("/views/Login/comp/LoginForm/Phone.jsx");
        files.add("/views/Login/comp/LoginForm/index.jsx");
        files.add("/views/Login/comp/LoginForm/Account.jsx");
        files.add("/views/Login/comp/Browser/index.scss");
        files.add("/views/Login/comp/Browser/index.jsx");
        files.add("/views/Login/comp/Accessory/index.scss");
        files.add("/views/Login/comp/Accessory/index.jsx");
        files.add("/views/Login/comp/Bottom/index.scss");
        files.add("/views/Login/comp/Bottom/index.jsx");
        files.add("/views/Login/index.jsx");
        files.add("/utils/typeCreater.js");
        files.add("/utils/iconfont.js");
        files.add("/utils/authorized.js");
        files.add("/utils/getKetcherGraph.js");
        files.add("/utils/user.js");
        files.add("/utils/graph/dijkstra.js");
        files.add("/utils/graph/index.js");
        files.add("/utils/debounce.js");
        files.add("/utils/noImage.js");
        files.add("/utils/localStorage.js");
        files.add("/utils/timestampToTime.js");
        files.add("/utils/screenfull.js");
        files.add("/utils/position.js");
        files.add("/service/apis.js");
        files.add("/service/http.js");
        files.add("/service/apis.knowledge.js");
        files.add("/service/apis.patent.js");
    }

    private static String basePath = "/Users/xiaomanwang/workspace/aidrug-react-font/src/";

    // 已经处理好的中英文对照文件
    private static String lineEnFileName = "/Users/xiaomanwang/资料/front-en-release/front-ch-en-line.txt";
    
    
    public static Set<String> getFileCn(File file) throws Exception {
        List<String> lines = Files.readLines(file, Charset.defaultCharset());
        Set<String> result = new HashSet<>();

        Pattern pattern = Pattern.compile(
                "[^\u4E00-\u9FA5\\u3002\\uff1b\\uff0c\\uff1a\\u201c\\u201d\\uff08\\uff09\\u3001\\uff1f\\u300a\\u300b]");

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            String lineResult = matcher.replaceAll(" ");
            if (!StringUtils.isEmpty(lineResult.trim())) {
                List<String> lineList = Splitter.onPattern("\\s+").splitToList(lineResult.trim());
                for (String l : lineList) {
                    result.add(l);
                }
            }
        }
        return result;
    }

    public static List<String> getFileCnLine(File file) throws Exception {
        List<String> lines = Files.readLines(file, Charset.defaultCharset());
        List<String> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(
                "[^\u4E00-\u9FA5\\u3002\\uff1b\\uff0c\\uff1a\\u201c\\u201d\\uff08\\uff09\\u3001\\uff1f\\u300a\\u300b]");

        int i = 0;
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            String lineResult = matcher.replaceAll(" ");
            if (!StringUtils.isEmpty(lineResult.trim())) {
                List<String> lineList = Splitter.onPattern("\\s+").splitToList(lineResult.trim());
                i++;
                for (String l : lineList) {
                    result.add(i + "#" + l);
                }

            } else {
                i++;
            }
        }
        return result;
    }

    public static void listAll(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {// 如果遍历到当前的file对象是个目录，继续遍历。
                listAll(file);
            } else {
                System.out.println("file:" + file.getAbsolutePath());
            }
        }
    }


    public static void getAllFileName() {
        String[] filePath = {"config", "views", "utils","service"};
        for (String f : filePath) {
            listAll(new File(basePath + f));
        }
    }

    public static void getLineChinese() throws Exception {
        // 打印有中文的文件
        for (String f : files) {
            File file = new File(basePath + f);

            List<String> fls = getFileCnLine(file);
            if (!CollectionUtils.isEmpty(fls)) {
                System.out.println("FileName:" + file.getAbsolutePath());
                for (String fl : fls) {
                    System.out.println(fl);
                }
                System.out.println("-------------");
            }
        }
    }

    public static void getUniqChinese() throws Exception {
        Set<String> words = new HashSet<>();
        for (String f : files) {
            File file = new File(basePath + f);
//            System.out.println(file.getName() + ":" + file.getAbsolutePath());
            words.addAll(getFileCn(file));
            System.out.println();
        }

//        System.out.println("===================");
        for (String s : words) {
            if (StringUtils.isNotBlank(s)) {
                System.out.println(s);
            }
        }
    }

    public static void replaceEnToFile() throws Exception {
        
        File lineEnFile = new File(lineEnFileName);
        List<String> lines = Files.readLines(lineEnFile, Charset.defaultCharset());

        // 获取一个文件为key值，每行数据为value的集合
        Map<String, List<String>> fileCnEnMap = new HashMap<>();
        Iterator<String> iter = lines.iterator();
        String fileName = StringUtils.EMPTY;
        while (iter.hasNext()) {
            String l = iter.next();
            if (l.contains("FileName")) {
                fileName = l.replace("FileName:", "").replace(basePath, "");
                fileCnEnMap.put(fileName, new ArrayList<>());
            } else {
                fileCnEnMap.get(fileName).add(l);
            }
        }

        // 解析文件做替换
        for (String fName : files) {
            File f = new File(basePath + fName);
//            File fnew = new File(basePath + fName + "-new");

            // 整个文件替换英文
            List<String> lineContents = fileCnEnMap.get(fName);
            if (!CollectionUtils.isEmpty(lineContents)) {
                Map<Integer, List<LineData>> lineDataMap = convert(lineContents);

                List<String> newLines = new ArrayList<>();
                List<String> flines = Files.readLines(f, Charset.defaultCharset());
                for (int i = 0; i < flines.size(); i++) {
                    int line = i + 1;
                    List<LineData> replaceDatas = lineDataMap.get(line);
                    if (!CollectionUtils.isEmpty(replaceDatas)) {
                        String replaceLine = flines.get(i);
                        String rpfinishLine = replaceLine;
                        for (LineData ld : replaceDatas) {
                            rpfinishLine = rpfinishLine.replace(ld.getCh(), ld.getEn());
                        }
                        newLines.add(rpfinishLine);
                    } else {
                        newLines.add(flines.get(i));
                    }
                }

                // 写入到一个新文件中
                CharSink sink = Files.asCharSink(f, Charsets.UTF_8);
                sink.writeLines(newLines);
            }
        }
    }

    public static Map<Integer, List<LineData>> convert(List<String> lineContents) {
        Map<Integer, List<LineData>> result = new HashMap<>();
        for (String content : lineContents) {
            LineData ld = new LineData(content);
            List<LineData> lds = result.getOrDefault(ld.getLineNum(), new ArrayList<LineData>());
            lds.add(ld);
            result.put(ld.getLineNum(), lds);
        }
        return result;
    }

    public static void replaceLineFile() throws Exception {
        // 翻译结果的文件
        String fName = "/Users/xiaomanwang/资料/front-en-release/front-ch-uniq-en.txt";
        // 带有行的中文抽取文件
        String lineFileName = "/Users/xiaomanwang/资料/front-en-release/front-ch-line.txt";
        // 匹配生成带有行的英文翻译的文件
        String lineEnFileName = "/Users/xiaomanwang/资料/front-en-release/front-ch-en-line.txt";

        // 获取中英翻译的KV集合
        File enFile = new File(fName);
        List<String> ens = Files.readLines(enFile, Charset.defaultCharset());
        Map<String, String> enMap = new HashMap<>();
        for (String en : ens) {
            List<String> arrays = Splitter.on("#").splitToList(en);
            if (!CollectionUtils.isEmpty(arrays) && arrays.size() > 1) {
                enMap.put(arrays.get(0), arrays.get(1));
            }
        }

        // 匹配中文并且拼接为合适的字符串
        File lineFile = new File(lineFileName);
        List<String> lines = Files.readLines(lineFile, Charset.defaultCharset());
        List<String> lineResult = new ArrayList<>();
        for (String l : lines) {
            List<String> larray = Splitter.on("#").splitToList(l);
            if (!CollectionUtils.isEmpty(larray) && larray.size() > 1) {
                String key = larray.get(1);
                String value = enMap.get(key);
                if (StringUtils.isNotBlank(value)) {
                    lineResult.add(l + "#" + value);
                }
            } else {
                lineResult.add(l);
            }
        }

        // 写入新文件中
        File lineEnFile = new File(lineEnFileName);
        CharSink sink = Files.asCharSink(lineEnFile, Charsets.UTF_8);
        sink.writeLines(lineResult);
    }

    public static void fackToEnglish() throws Exception {
        String fileName = "/Users/xiaomanwang/资料/front-english/front-chinese.txt";
        String fName = "/Users/xiaomanwang/资料/front-english/front-ch-en-result.txt";
        File file = new File(fileName);
        File newFile = new File(fName);
        List<String> lines = Files.readLines(file, Charset.defaultCharset());
        List<String> ll =
                lines.stream().map(l -> l + "#" + cn2Spell(l)).collect(Collectors.toList());
        CharSink sink = Files.asCharSink(newFile, Charsets.UTF_8);
        sink.writeLines(ll);
    }

    public static String cn2Spell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                    pybf.append(arr[i]);
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString();
    }



    public static void main(String[] args) throws Exception {

        // 获取文件目录
//         getAllFileName();

        // 获取每行的中文
         getLineChinese();

        // 获取去重的中文
//         getUniqChinese();

        // 模拟英文结果
//         fackToEnglish();

        // 把英文替换到行文件后面
//         replaceLineFile();

        // 将英文的行文件，替换生成新文件
//        replaceEnToFile();

    }
}

