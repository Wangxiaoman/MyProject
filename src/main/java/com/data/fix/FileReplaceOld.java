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


public class FileReplaceOld {
    public static List<String> files = new ArrayList<>();
    static {
        files.add("page/home/ocrHome.vue");
        files.add("page/home/jsme.nocache.js");
        files.add("page/home/home.vue");
        files.add("page/home/jsme.devmode.js");
        files.add("page/analysis/index.vue");
        files.add("page/analysis/synthesisPath.vue");
        files.add("page/searching/index.vue");
        files.add("page/searching/historypage.vue");
        files.add("page/searching/projectmanage.vue");
        files.add("page/searching/noAuth.vue");
        files.add("page/searching/optimizeResult.vue");
        files.add("page/searching/css/arrow.css");
        files.add("page/searching/applyvip.vue");
        files.add("page/searching/taskdetail.vue");
        files.add("page/searching/molecularOptimize.vue");
        files.add("page/searching/scaffoldHopping.vue");
        files.add("page/searching/moleculeDetail/moleculeDetail.vue");
        files.add("page/searching/moleculeDetail/abstractModule.vue");
        files.add("page/searching/moleculeDetail/moleculeDetails/tModule.vue");
        files.add("page/searching/moleculeDetail/moleculeDetails/dmpkModule.vue");
        files.add("page/searching/moleculeDetail/moleculeDetails/structModule.vue");
        files.add("page/searching/moleculeDetail/moleculeDetails/detailModule.vue");
        files.add("page/searching/moleculeDetail/moleculeDetails/physicalModule.vue");
        files.add("page/searching/moleculeDetail/moleculeDetails/rankModule.vue");
        files.add("page/searching/moleculeDetail/taskmoleculedetail.vue");
        files.add("page/searching/skeletonDerivation.vue");
        files.add("page/searching/derivationResult.vue");
        files.add("page/searching/molecularmanagement.vue");
        files.add("page/searching/homepage.vue");
        files.add("page/searching/moleculeDocking.vue");
        files.add("page/searching/scaffoldhoppingResult.vue");
        files.add("page/layout/pageHeader.vue");
        files.add("page/layout/layoutUser.vue");
        files.add("page/layout/pageFooter.vue");
        files.add("page/layout/layout.vue");
        files.add("page/layout/appMain.vue");
        files.add("page/layout/loginFooter.vue");
        files.add("page/layout/step.js");
        files.add("page/ocr/css/arrow.css");
        files.add("page/ocr/ocr.vue");
        files.add("page/ocr/readPDF.vue");
        files.add("page/ocr/ocrRead.vue");
        files.add("page/user/index.vue");
        files.add("page/user/collect.vue");
        files.add("page/user/noOrganization.vue");
        files.add("page/user/enterpriseEdition.vue");
        files.add("page/user/enterpriseintro.vue");
        files.add("page/user/membermanage.vue");
        files.add("page/user/history.vue");
        files.add("page/user/userinfo.vue");
        files.add("page/user/userCenter.vue");
        files.add("page/user/organization.vue");
        files.add("page/user/security.vue");
        files.add("page/user/completesteps.js");
        files.add("page/newocr/index.vue");
        files.add("page/newocr/result/result.vue");
        files.add("page/newocr/result/resultFile.vue");
        files.add("page/newocr/result/resultTable.vue");
        files.add("page/newocr/event.js");
        files.add("page/newocr/nameExtraction.vue");
        files.add("page/newocr/ocrTaskdetail.vue");
        files.add("page/newocr/ocrJoblist.vue");
        files.add("page/newocr/patentExtraction.vue");
        files.add("page/newocr/upload/upload.vue");
        files.add("page/login/login.vue");
        files.add("page/login/register.vue");
        files.add("page/login/review.vue");
        files.add("page/login/resetEmail.vue");
        // files.add("page/login/privacy.vue");
        files.add("page/login/forgetPassword.vue");
        files.add("page/login/institution.vue");
        files.add("page/login/active.vue");
        // files.add("page/login/protocal.vue");
        files.add("page/login/loginHeader.vue");
        files.add("page/dmpkt/index.vue");
        files.add("page/dmpkt/dmpkList.vue");
        files.add("page/dmpkt/dmpktInput.vue");
        files.add("components/pathItem.vue");
        files.add("components/fullShow.vue");
        files.add("components/feedbackModal.vue");
        files.add("components/experimentModal.vue");
        files.add("components/errorSmiles.vue");
        files.add("components/modifyModal.vue");
        files.add("components/collectStar.vue");
        files.add("components/jsmeInput.vue");
        files.add("components/errorToolpit.vue");
        files.add("components/deveLop.vue");
        files.add("components/draggableBorder.vue");
        files.add("components/batchdockingModal.vue");
        files.add("components/targetSelect.vue");
        files.add("components/screentest.vue");
        files.add("components/resultItem.vue");
        files.add("components/screen.vue");
        files.add("components/optModal.vue");
        files.add("components/limitModal.vue");
        files.add("components/uploadInput.vue");
        files.add("components/dockModal.vue");
        files.add("components/EditableCell.vue");
        files.add("components/modifyMessage.vue");
        files.add("components/identify.vue");
        files.add("modules/ocrtestBorder.vue");
        files.add("router/index.js");
    }

    private static String basePath = "/Users/xiaomanwang/workspace/aidrug-font/src/";

    // 已经处理好的中英文对照文件
    private static String lineEnFileName = "/Users/xiaomanwang/资料/front-en-release/old/front-ch-en-line.txt";
    
    
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
        String[] filePath = {"page", "components", "modules", "router"};
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
        String fName = "/Users/xiaomanwang/资料/front-en-release/old/front-old-en.txt";
        // 带有行的中文抽取文件
        String lineFileName = "/Users/xiaomanwang/资料/front-en-release/old/front-old-line.txt";
        // 匹配生成带有行的英文翻译的文件
        String lineEnFileName = "/Users/xiaomanwang/资料/front-en-release/old/front-ch-en-line.txt";

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
    
    
    public static void checkHashtag() throws Exception{
        String fileName = "/Users/xiaomanwang/资料/front-en-release/old/front-old-en.txt";
        File file = new File(fileName);
        List<String> lines = Files.readLines(file, Charset.defaultCharset());
        if(!CollectionUtils.isEmpty(lines)) {
            for(String l : lines) {
                int count = 0;
                for(int i=0;i<l.length();i++) {
                    if(l.charAt(i) == '#') {
                        count = count + 1;
                    }
                }
                if(count >=2 ) {
                    System.out.println(l);
                }
            }
        }
    }



    public static void main(String[] args) throws Exception {

        // 获取文件目录
        // getAllFileName();

        // 获取每行的中文
//         getLineChinese();

//         获取去重的中文
//         getUniqChinese();

        // 模拟英文结果
//         fackToEnglish();

        // 把英文替换到行文件后面
//         replaceLineFile();

        // 将英文的行文件，替换生成新文件
//        replaceEnToFile();
        
        checkHashtag();
    }
}
