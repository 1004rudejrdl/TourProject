-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: projectdb
-- ------------------------------------------------------
-- Server version	5.7.10-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `finallist`
--

DROP TABLE IF EXISTS `finallist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finallist` (
  `tourID` char(20) NOT NULL,
  `tourName` char(20) NOT NULL,
  `tourClass` char(20) NOT NULL,
  `tourCharge` int(11) NOT NULL,
  KEY `tourID` (`tourID`),
  CONSTRAINT `finallist_ibfk_1` FOREIGN KEY (`tourID`) REFERENCES `tourlist` (`tourID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finallist`
--

LOCK TABLES `finallist` WRITE;
/*!40000 ALTER TABLE `finallist` DISABLE KEYS */;
/*!40000 ALTER TABLE `finallist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `infortour`
--

DROP TABLE IF EXISTS `infortour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `infortour` (
  `tourID` char(20) NOT NULL,
  `tourName` char(20) NOT NULL,
  `tourAddr` varchar(100) NOT NULL,
  `tourWay` varchar(300) NOT NULL,
  `tourExplain` varchar(500) NOT NULL,
  `tourImage` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `infortour`
--

LOCK TABLES `infortour` WRITE;
/*!40000 ALTER TABLE `infortour` DISABLE KEYS */;
INSERT INTO `infortour` VALUES ('8103tokyo01','신주쿠','도쿄 도 신주쿠 구 신주쿠역 근방','JR 신주쿠(新宿)역 또는 \r\n지하철 도에이신주쿠(都営新宿)·마루노우치(丸の內)·도에이오에도(都営大江戶)선\r\n신주쿠(新宿)역에서 바로 연결','신주쿠는 우리나라 강남처럼 계획에 의해 발전한 부도심이다.\r\n급속한 경제발전을 이룬 일본의 모습을 한눈에 실감할 수 있는\r\n일본 최대의 번화가이다.','tokyo01.JPG'),('8103tokyo02','아사쿠사','도쿄 도 다이토 구 센소지 근방','지하철 긴자·도에이아사쿠사선 아사쿠사역 하차','아사쿠사는 현대화의 극치를 달리는 도쿄에서 전통적인 색깔을 잘 간직하여 많은 사람이 찾는 곳이다.\r\n예로부터 우에노와 더불어 서민의 정취를 풍기는 시타마치(下町)의 중심지이기도 하다.','tokyo02.JPG'),('8103tokyo03','디즈니','Urayasu,Chiba Prefecture 279-0031','게이요(京葉)선 마이하마(舞浜)역 하차','1983년에 개장했으며 미국 이외의 지역에서 건설된 첫번째 디즈니랜드이다.\r\n꿈과 마법의 왕국을 테마로 다양한 놀이시설과 흥겨운 이벤트가 가득하다.','tokyo03.JPG'),('8103tokyo04','하라주쿠','일본 도쿄 시부야 구 하라주쿠역 근방','JR 하라주쿠(原宿)역 하차','도쿄의 패션 1번지로 꼽히는 젊은이의 거리이다.\r\n일본 젊은층들의 패션과 유행을 한눈에 살펴볼 수 있는 거리이며\r\n사치스러운 면도 있지만 남의 눈을 의식하지 않는 젊은층들의 독특한 패션과 파리를 연상케 하는 색다른 매력을 선사한다.','tokyo04.JPG'),('8103tokyo05','오에도 온천','오다이바 오오에도온센 모노가타리','도쿄 텔레포트역에서 셔틀버스 이용','도쿄 도심 속에 위치하여 접근성이 좋고 에도시대 전통 분위기의 테마로 힐링여행에 적합한 곳이다.\r\n내부에는 먹거리와 볼거리가 흥미진진하고 보기와는 반대로 가격이 저렴한 편이다.','tokyo05.JPG'),('8103tokyo06','스카이 트리','도쿄 도 스미다 구','한조몬선 오시아게역 또는 스카이트리역','도쿄 스카이 트리는 일본 도쿄도 스미다 구에 세워진 전파탑이다.\r\n중국의 광저우타워를 제치고 세계에서 가장 높은 자립식 전파탑이다.','tokyo06.JPG'),('8103tokyo07','우에노 동물원','Uenokoen,Taitō,Tokyo 110-0007','우에노 오카치마치 역 또는 JR야메노테선을 이용','1882년 3월 일본 최초의 근대적 동물원으로서 개원하였다.\r\n1924년 궁내성의 기증을 알리기 위해 정식명칭은 도쿄도 은사 우에노동물원으로 되어 있다.\r\n외국의 진귀한 동물들을 잇따라 도입하면서 그 규모와 입장객수가 크게 증가하였다.','tokyo07.JPG'),('8103tokyo08','오다이바 대관람차','Aomi,Koto,Tokyo 135-0064','JR 시부야 신주쿠 이케부쿠로 역에서 린카이 선 탑승 후 도쿄 텔레포트 역에서 하차\r\n또는 JR 신바시 역에서 유리카모메 탑승 후 오다이바 역 하차','도쿄 베이 지역에 세운 거대한 타운으로 최근 몇 년간 최고의 인기를 구가하는 관광지이다.\r\n다이바라는 이름에서 알 수 있듯이 원래는 에도를 지키기 위해 세워졌지만\r\n현재는 각국의 관광객들이 몰려드는 거대한 상업지구로 탈바꿈했다.','tokyo08.JPG'),('8103tokyo09','네즈미술관','도쿄 도 미나토 구 미나미아오야마','Omotesando역 도쿄메트로를 이용하여\r\n오모테산도(表参道)역 A5번 출구에서\r\n도보로 약 8분소요','도부 철도 사장을 지낸 사업가 네즈 가이치로의 수집품을 전시하기 위해 1941년에 설립되었다.\r\n일본 내에서 전쟁 이전부터 역사가 이어져 오는 사립미술관 중 하나이며\r\n미술관 내에는 회화, 조각, 도예 등 다양한 동양 고미술품들이 전시되어 있다.','tokyo09.JPG'),('8103tokyo10','도쿄 시티 뷰','미나토 구 롯폰기 6-10-1 롯폰기 힐스 타워','Omotesando역 도쿄메트로를 이용\r\n오모테산도(表参道)역 A5번 출구에서 도보로 약 8분소요','도쿄 중심에 위치해 있으며, 해발 250미터의 실내 전망회랑과 해발 270미터의 옥상 스카이테크가 있는 전망시설이다.\r\n날씨가 좋을 때는 후지산까지 한눈에 바라볼 수 있다.','tokyo10.JPG'),('8106osaka01','도톤보리','일본 혼슈 서부 오사카','지하철 난바역 하차 후 14번 출구','고급상점들이 즐비한 신사이바시와는 달리 서민적인 분위기를 느낄 수 있는 번화가이다.\r\n난바로 이어지는 에비스바시에서 동쪽의 닛폰바시에 이르는 지역에는 화려한 네온사인과 독특한 간판이 많다.','osaka01.JPG'),('8106osaka02','유니버셜 스튜디오','akurajima,Konohana Ward,Osaka, 554-0031','지하철 난바역에서 한신라인 탑승 후 나시쿠조역에 하차 후\r\nJR선으로 갈아탄 후 유니바사루시티역에서 하차','1990년 플로리다주 올랜도에 개장된 유니버셜 스튜디오\r\n플로리다에 이어 세계에서 세번째, 미국 국외에서는 최초로 건설한 유니버셜 스튜디오이다.\r\n2001년 3월에 개장하였다.','osaka02.JPG'),('8106osaka03','신사이바시','Minamisenba,Chuo Ward,Osaka, 542-0081','지하철 미도스지(御堂筋)선 신사이바시(心斎橋)역 하차','오사카의 대표적인 쇼핑지역이다.\r\n난바와 더불어 미나미에서 가장 번화한 곳으로, 신사이바시스지 상점가가 남북으로 길게 자리잡고 있으며\r\n대로인 미도스지가 그 옆으로 나란히 뻗어 있다.','osaka03.JPG'),('8106osaka04','오사카성','大阪市中央区大阪城1-1','JR오사카조코엔(大阪城公園)역, JR모리노미야(森ノ宮)역에서 하차','오사카의 상징인 오사카 성은 16세기 도요토미 히데요시가 일본 통일을 달성한 후 권력을 과시하기 위해 지은 성으로\r\n완성 당시 금박 장식으로 뒤덮인 호화로운 모습이었으나\r\n이후 소실과 재건의 역사를 거쳤다.','osaka04.JPG'),('8106osaka05','츠텐카쿠','Ebisuhigashi, Naniwa Ward,Osaka, 556-0002','지하철 사카이스지선 에비스초역 3번출구','현재의 츠텐카쿠는 두 번째 지어진 것으로\r\n츠텐카쿠 관광주식회사가 운영을 맡고 있다.\r\n츠텐카쿠라는 이름은 하늘에 통하는 높은 건물이라는 의미이다.','osaka05.JPG'),('8106osaka06','덴포잔 대관람차','Kaigandori,Minato Ward,Osaka, 052-0022','지하철 오사카코역 1번 출구','직경 100m에 달하는 세계 최대 규모의 관람차이다.\r\n15분간 펼쳐지는 하늘에서의 여행과\r\n마치 불꽃이 쏘아올려진 듯한 아름다움을 자랑한다.','osaka06.JPG'),('8106osaka07','우메다 전망대','531-6023, Osaka,Kita,Oyodonaka','난바에서 미도스지선을 탑승 후 우메다역 5번출구','우메다는 일본 오사카시 기타구의 상업과 업무지구로,\r\n도시의 주요 교통에는 북쪽 철도 터미널인 오사카 역과 우메다 역이 있다.\r\n커다한 철도 축일 뿐만 아니라 주요 관청과 호텔 지구이기도 하다.','osaka07.JPG'),('8106osaka08','오사카 주택 박물관','530-0041, Osaka,Kita,Tenjinbashi','난바역에서 덴진바시스 로쿠초메역 3번 출구 건물 8~10층','도시 거주에 대한 역사와 문화를 테마로 한 일본에서 최초로 개관한\r\n오사카부 오사카 시 기타 구에 위치한 박문관이다.\r\n에도 시대 후기부터 전후에 걸쳐\r\n주거에 관한 자료와 모형들이 전시되어 있다.','osaka08.JPG'),('8106osaka09','센니치마에 도구야스지','14-5 Nanbasennichimae,Chuo Ward,Osaka, 542-0075','난바역 2번 출구에서 도보 5분거리','요리도구, 주방기구가 모두 갖추어진 전문점이 늘어서 있는\r\n길이 150m의 상점가.\r\n일본의 도구의 날 10월 9일에는 도구야스지 축제가 개최하여\r\n다양한 도구를 저렴한 가격에 판매한다.','osaka09.JPG'),('8106osaka10','도톤보리 리버크루즈','542-0084 Osaka,Chuo Ward,Souemoncho, 7','난바역, 신사이바시역, 닛폰바시 역에서 도보 약 5~10분 소요','가이드 승선원을 선두로 도톤보리에서 출항한다.\r\n약 20분간 도톤보리 미니 크루징을 오사카 가이드 승선원이 안내한다.','osaka10.JPG'),('8111sapporo01','스스키노','삿포로시 추오구 미나미 4조 니시 4','지하철 난보쿠선 스스키노역 또는\r\n지하철 도호선 호수이스스키노역 하차','쥬오쿠에 있는 삿포로시 최대의 번화가이다.\r\n메이지 시대, 훗카이도 개척 당시에 7채의 기루에서 시작된 이 거리는\r\n훗카이도 개척 130년의 역사와 함께 번화해 왔다.','sapporo01.JPG'),('8111sapporo02','삿포로 맥주 박물관','홋카이도 삿포로시 히가시구 기타7조히가시 9-1-1','삿포로 역에서 도보 10분소요','훗카이도 삿포로 시 히가시 구 삿포로 가든 파크 내에 위치한 박물관이다.\r\n일본에선 유명한 맥주 박물관이며, 훗카이도 유산의 하나로도 지정되어 있다.','sapporo02.JPG'),('8111sapporo03','삿포로 텔레비전 타워','060-0042 삿포로시주오쿠오도리니시 1초메','삿포로 지하철 오도리 역(도자이선, 난보쿠선)에서 27번 출구,\r\n도보 5분 소요','훗카이도 삿포로 시 주오 구에 위치한 텔레비전 전파탑이다.\r\n지하에는 식당, 1층에는 오락실, 2층에는 다목적 홀, 3층에는 기념품 판매점이 있다.','sapporo03.JPG'),('8111sapporo04','오도리 공원','060-0042 Hokkaido, Sapporo, Chuo Ward, 大通西7丁目','삿포로 지하철 오도리 역 오도리 공원 6번 출구','산책과 점심시간 등 삿포로 시민의 쉼터가 되어 주는 공원이다.\r\n계절마다 이벤트가 열려 사계절을 제대로 느낄 수 있다.','sapporo04.JPG'),('8111sapporo05','다이마루','060-0005 Hokkaido, Sapporo, Chuo Ward, Kita 5 Jonishi','JR 삿포로 미나미구치역','일본의 대표적인 백화점 브랜드로 다이마루 삿포로 점은\r\n삿포로 역, JR타워와 스텔라 플레이스와 함께 있는데\r\n 건물 간 이동 및 접근성이 가장 편리하게 되어 있는 백화점이다.','sapporo05.JPG'),('8111sapporo06','훗카이도 대학','Kita 8 Jonishi, Kita, Sapporo, Hokkaido 060-0808','삿포로 역 도보로 7분 소요','훗카이도 대학은 1918년 설립된 일본의 국립대학이다.\r\n호쿠다이라는 약칭으로 알려져 있으며,\r\n일본의 구 국제대학 중 하나이다.','sapporo06.JPG'),('8111sapporo07','시로이코이비토 파크','Miyanosawa 2 Jo,Sapporo,Hokkaido 063-0052','지하철 도자이선 마야노사 역에서 하차 후 도보로 약 7분','초콜릿 및 쿠키 샘플과 전시관이 있어\r\n가족 명소로 유명하여 트레인 놀이기구와 정원이 있다.','sapporo07.JPG'),('8111sapporo08','삿포로 시계탑',' Kita 1 Jonishi, Chuo Ward, Sapporo, Hokkaido 060-0001','JR 삿포로(札幌)역 남쪽 출구에서 도보 10분,\r\n시영 지하철 오도리(大通)역에서 하차 후\r\n시청(市役所) 쪽 출구로 나와 도보 5분 ','훗카이도 삿포로 시 주오 구에 위치한 시계탑이다.\r\n일본의 중요문화재로 등록되어 있고 정식 명칭은 구 삿포로 농학교 연무장이며,\r\n통칭은 삿포로 시계탑이다.\r\n삿포로 중심부에 유명한 관광 명소이다.','sapporo08.JPG'),('8111sapporo09','훗카이도청 구 본청사','Kita 3 Jonishi, Chuo Ward, Sapporo, Hokkaido 060-8588','지하철 난보쿠선, 도호선, JR선 삿포로 하차 후 도보 8분','한때 훗카이도의 청사로 사용되던 건축물로 관내는\r\n훗카이도 개척 관계 자료를 전시, 보존하는 훗카이도립 문서관이 있어\r\n도청 회의실로도 사용하고 있으며,\r\n일반인에게 공개되고 있다.','sapporo09.JPG'),('8111sapporo10','JR타워 전망대','060-0005 Hokkaido, Sapporo, Chuo Ward, Kita 5 Jonishi','삿포로역의 스텔라플레이스 6층으로 이동 후\r\nJR타워와 연결되는 통로 통과','삿포로의 야경을 볼 수 있는 가장 높은 빌딩이다.\r\n높이 174m, 지상 38층으로 이루어진 이 빌딩은 38층에 전망대가 있다.','sapporo10.JPG'),('8192fukuoka01','오호리 코엔 정원','Ohorikoen, Chuo Ward, Fukuoka,810-0051','하카타역 공항선 이용 탑승 후 오호리코엔역 하차','후쿠오카 현 후쿠오카 시 주오 구에 있는 공원 면적의 약 60%를 차지하고 있는 물의 공원이다.\r\n원래는 적의 공격을 막기 위해 만든 것으로 중국의 시 호를 모방한 것이다.','fukuoka01.JPG'),('8192fukuoka02','커낼시티','Sumiyoshi, Hakata Ward, Fukuoka,812-0018','하카타역에서 100엔 버스 탑승 후 3정거장 후\r\n커낼시티 하카타 정류장 하차','후쿠오카 현 후쿠오카 시 하카타 구에 있는 복합시설이다.\r\n오피스동과 호텔, 상업시설 등이 늘어서 있으며,\r\n커낼시티 극장과 라멘 스튜디오 등이 있다.','fukuoka02.JPG'),('8192fukuoka03','나카스 포장마차거리','810-0801 福岡市博多区中洲 那珂川 通り','하카타(博多)역에서 도보 5분,\r\n지하철 텐진(天神)역에서 도보 5분','나카스는 강의 모래톱이라는 의미로,\r\n나카가와와 하카타가와 사이에 위치한 작은 섬이다.\r\n강가에 늘어서 있는 포장마차 야타이는 나카스를 찾는 또 다른 즐거움이다.','fukuoka03.JPG'),('8192fukuoka04','모모치 해변','814-0001,Sawara Ward,Momochihama2丁目,4丁目地先','버스정류장 Hakata Sta.A 302번, 303번,\r\n305번 버스 약 35분 소요','도시와 자연이 가까운 거리에 후쿠오카에서는\r\n도시 중심부에서 차로 15분만 가면 바다가 보인다.\r\n그곳이 바로 후쿠오카를 대표하는 해변가 시사이드 모모치 해변공원이다.','fukuoka04.JPG'),('8192fukuoka05','후쿠오카 타워','Momochihama, Sawara Ward,814-0001','Hakata Sta Terminal 306번 버스 약 30분 소요','후쿠오카 시 사와라 구의 사이드 모모치 지구의\r\nRKB 마이니치 방송의 본사 내에 있는 랜드마크 타워로 높이는 234m이다.\r\n일본에서 제일 높은 해변가 타워이기도 하다.','fukuoka05.JPG'),('8192fukuoka06','하우스텐보스',' Huis Ten Bosch Machi, Sasebo,859-3292','하카타역에서 사세보행 미도리 선 열차 탑승 후 하이키역에서 하차,\r\n후에 하우스텐보스행 오무라 선 열차로 환승,\r\n하우스텐보스역에서 하차','약 45만평의 넓은 부지에 40만 그루의 나무와 30만 종의 화초가 자라고\r\n길이 6km, 너비 20m, 깊이 5m의 운하가 있다.\r\n17세기 네덜란드의 왕궁과 거리를 재현하여 일본 속의 네덜란드라 불린다.','fukuoka06.JPG'),('8192fukuoka07','마리아노시티','819-0001, Fukuoka, Nishi Ward, Odo, 2−12−30','하카타, 텐진(博多、天神)에서 303번 버스,\r\n지하철 구코센(空港線) 메이노하마(姪浜)역에서\r\n100円 쇼와버스(昭和バス) 이용\r\n(마리아노시티에서 쇼와 버스를 50円에 이용할 수 있는 쿠폰을 무료로 배포)','마리아노 시티는 2000년 10월에 개장한 규슈 최대 규모의 아울렛 매장이다.\r\n후쿠오카의 새로운 명소로 인기를 모으고 있다.\r\n쇼핑보다 더 인기를 끌고 있는 관람차는\r\n작은 관람차와 큰 관람차가 마주 보는 특이한 형태이다.','fukuoka07.JPG'),('8192fukuoka08','마린월드','811-0321 Fukuoka, Higashi Ward, 西戸崎１８−２８','JR하카타역 탑승 후 JR우미노나카미치역 하차','다양한 해양생물을 볼 수 있는 도심 수족관으로\r\n매일 돌고래와 바다사자쇼가 펼쳐지는 공연장이 있다.','fukuoka08.JPG'),('8192fukuoka09','라쿠스이엔','후쿠오카시 하카타구 스미요시 2-10-7','JR하카타역에서 도보 12분 또는\r\n니시테쓰버스 하카타에키마에욘초메에서 하차 후 도보 7분','하카타구의 스미요시신사의 북쪽에 위치한 일본정원이다. 메이지시대에 세워진 하카타 상인의 별장을 다실 건물로서\r\n개축하여 사계절의 자연과 연못에 둘러싸여\r\n부드럽고 그윽한 향이 감도는 아름다운 일본정원이다.','fukuoka09.JPG'),('8192fukuoka10','아사히 맥주공장','Takeshita, Hakata Ward, Fukuoka812-0895','하카타역 JR구루메행 열차 탑승 후\r\n다케시타역에서 하차, 도보 5분 소요','아사히 맥주공장은 안내 담당자가 맥주가 생산되는 과정을 안내한다.\r\n영상을 통한 아사히 맥주 공장 해설, 원료 전시, 제조공정견학,\r\n마지막엔 아사히 슈퍼드라이 시음도 할 수 있다.','fukuoka10.JPG'),('8198okinawa01','아메리칸 빌리지','Mihama, Nakagami-gun, Chatan-cho 904-0115','나하 공항(那覇空港)에서 차로 약 40분','아메리칸 빌리지는 오키나와의 오다이바라고 불리는 자탄초의 미하마 지역에 위치한 복합 타운이다.\r\n1981년에 반환된 미군 비행장 부지에 계획적으로 조성된 시티 리조트로,\r\n미국 샌디에이고의 시포트 빌리지를 모델로 하고 있다.','okinawa01.JPG'),('8198okinawa02','슈리성','오키나와현 나하시 슈리 칸죠초 1-2','어떤 방면이든 슈리방면으로 가는 모노레일 탑승.\r\n(슈리성은 모노레일 종점에 위치하기 때문)','오키나와현 나하 시에 있으며,\r\n예전에는 해외무역의 거점인 나하 항을 내려보는 언덕에 있던 성이다.\r\n1945년 오키나와 전투와 전쟁 후에 완벽하게 파괴되어\r\n1992년 세이덴 등 옛 유적을 묻어서 되돌리는 형태로 복원되었다.','okinawa02.JPG'),('8198okinawa03','츄마우리 수족관','Ishikawa, Motobu, Kunigami District,905-0206','렌터카 또는 자가용 이용 선호,\r\n대중교통 이용시 얀바루 급행 버스 이용','오키나와 추마우리 수족관은 오키나와현 모토부 정에 위치한 수족관이다.\r\n2010년 3월 20일 2억번째 관람객을 맞이하였고\r\n2005년도까지 세계에서 가장 넓은 수족관(2헥타르)이었다.','okinawa03.JPG'),('8198okinawa04','오키나와 월드','901-0616 Nanjo, 玉城前川1336','아사히바시역 하차 후 동쪽출구로 나간 후\r\n왼쪽으로 돌고 5분정도 소요되는 거리의 정류장에서\r\n83번 버스 탑승','오키나와의 자연과 예능, 문화를 한꺼번에 체험할 수 있는 관광시설이다.\r\n일본의 유형문화재로도 지정되어있는 류큐왕국죠카마치는\r\n100년전에 지어진 낡은 민가를 여러 채 이축하여\r\n옛날 오키나와의 마을 풍경을 재현한 것이다.','okinawa04.JPG'),('8198okinawa05','오리온 맥주공장','Nago, Okinawa Prefecture 905-0021','나하 버스 터미널 14번 게이트에서\r\n120번 버스를 타고\r\n오리온 맥주 공장 근처 정류장 하차','1957년에 설립한 오리온 맥주공장은\r\n공장내를 가이드를 통해서 견학을 할 수 있다.\r\n신선한 오리온 맥주를 경험할 수 있고\r\n오리온 맥주의 역사를 체험할 수 있다.','okinawa05.JPG'),('8198okinawa06','비세노 후쿠기나미키','규슈 오키나와현 쿠니가미군 모토부초 비세','나고버스터미널 이용','무더운 여름 날 더위를 피해 바닷가를 찾아갔다가\r\n뭔가 부족한 느낌이 들 때, 이곳을 찾아가면 좋다.\r\n자연의 정취를 그대로 느낄 수 있는 나무와 숲이 많아\r\n오키나와의 숨겨진 관광명소이다.','okinawa06.JPG'),('8198okinawa07','세이화우타키','난조시 지넨 구데켄 270-1','나하 국제선청사 앞 111번 버스 탑승\r\n또는 나하버스터미널 10번 탑승장에서 38번 버스 탑승','우타키는 오키나와에있는 성역으로 마을 공동체가 제사를 지내는 곳이며,\r\n세이화우타키는 류큐왕국 제일의 신성지역으로,\r\n사람들은 이곳으로 조상신이 찾아온다고 믿는다.','okinawa07.JPG'),('8198okinawa08','이케이비치','규슈 오키나와현 우루마시 요나시로이케이 405','자가용 또는 렌터카 이용','오키나와 본도에서 양쪽에 바다가 펼쳐지는\r\n드라이브코스로서도 유명한 해중도로를 지나,\r\n2개의 섬을 건너면 이케이 비치가 보인다.\r\n바다의 투명도가 압권으로 다양한 레저를 즐길 수 있다.','okinawa08.JPG'),('8198okinawa09','류큐무라 민속촌','沖縄県国頭郡恩納村山田1130','나하 버스터미널에서 20번 버스 승차\r\n류큐무라(琉球村)에서 하차','류큐무라는 계승하고 싶은 옛날의 오키나와를 테마로,\r\n약 100년 전 류큐 왕국 시대의 민가를 한자리에 모아놓은 민속촌이다.\r\n오키나와 열도 각지에 흩어져 있던 민가를 이축해 조성한 곳이기도 하다.','okinawa09.JPG'),('8198okinawa10','무라사키무라','Takashiho, Yomitan, Nakagami District 904-0323','나하 BT 28:29번 선에서 요미탄 BT에서 하차 후\r\n택시로 3분 소요','체험 왕국 무라사키무라는\r\n1993년에 방송된 일본 대하 드라마 류큐의 바람을 촬영하기 위해 지어졌던 장소이다.\r\n오키나와를 체험할 수 있는 관광지로 체험 메뉴는 약 100가지 이상이다.','okinawa10.JPG');
/*!40000 ALTER TABLE `infortour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `memberlist`
--

DROP TABLE IF EXISTS `memberlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `memberlist` (
  `memberID` char(20) NOT NULL,
  `memberPassWord` char(20) NOT NULL,
  `memberName` char(20) NOT NULL,
  `memberGender` char(5) NOT NULL,
  `memberPhone` char(20) NOT NULL,
  `memberEmail` varchar(100) NOT NULL,
  PRIMARY KEY (`memberID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memberlist`
--

LOCK TABLES `memberlist` WRITE;
/*!40000 ALTER TABLE `memberlist` DISABLE KEYS */;
INSERT INTO `memberlist` VALUES ('cow7485','123123','민제경','남성','010-3572-7485','cow7485@naver.com'),('dmsdudwo1002','12345678','은영재','남성','010-3347-0000','dudwo1002@naver.com'),('juk6598','4697','적길동','여성','010-7777-8787','juk@naver.com'),('my','1','마이','남성','010-5555-4444','my@naver.com'),('qtx123','09876','김은경','여성','010-3618-2684','qtx123@hanmail.net'),('sks123','123456','홍길동','남성','010-1234-5678','hong@hanmail.net'),('toto','12','토토','남성','010-8888-7777','toto@naver.com'),('toy2684','123456','장승국','남성','010-8758-2683','0322684@naver.com'),('ujung419','123456','채유정','여성','010-2344-7338','ujung419@naver.com');
/*!40000 ALTER TABLE `memberlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mytourlist`
--

DROP TABLE IF EXISTS `mytourlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mytourlist` (
  `tourID` char(20) NOT NULL,
  `tourName` char(20) NOT NULL,
  PRIMARY KEY (`tourID`),
  CONSTRAINT `mytourlist_ibfk_1` FOREIGN KEY (`tourID`) REFERENCES `tourlist` (`tourID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mytourlist`
--

LOCK TABLES `mytourlist` WRITE;
/*!40000 ALTER TABLE `mytourlist` DISABLE KEYS */;
INSERT INTO `mytourlist` VALUES ('8103tokyo01','신주쿠'),('8103tokyo03','디즈니'),('8103tokyo05','오에도 온천'),('8111sapporo03','삿포로 텔레비전 타워'),('8192fukuoka06','하우스텐보스'),('8198okinawa03','츄마우리 수족관');
/*!40000 ALTER TABLE `mytourlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tourlist`
--

DROP TABLE IF EXISTS `tourlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tourlist` (
  `tourID` char(20) NOT NULL,
  `tourName` char(20) NOT NULL,
  `tourClass` char(20) NOT NULL,
  `tourCharge` int(11) NOT NULL,
  PRIMARY KEY (`tourID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tourlist`
--

LOCK TABLES `tourlist` WRITE;
/*!40000 ALTER TABLE `tourlist` DISABLE KEYS */;
INSERT INTO `tourlist` VALUES ('8103tokyo01','신주쿠','주요거리',0),('8103tokyo02','아사쿠자','신사 및 절',0),('8103tokyo03','디즈니','놀이시설',7400),('8103tokyo04','하라주쿠','주요거리',0),('8103tokyo05','오에도 온천','편의시설',2480),('8103tokyo06','도쿄 스카이 트리','주요시설',1030),('8103tokyo07','우에노 동물원','주요시설',600),('8103tokyo08','오다이바 대관람차','주요시설',1000),('8103tokyo09','네즈미술관','문화시설',1100),('8103tokyo10','도쿄 시티 뷰','주요시설',1800),('8106osaka01','도톤보리','주요거리',0),('8106osaka02','유니버셜 스튜디오','놀이시설',7400),('8106osaka03','신사이바시','주요거리',0),('8106osaka04','오사카성','주요시설',600),('8106osaka05','츠텐카쿠','주요시설',800),('8106osaka06','덴포잔 대관람차','놀이시설',800),('8106osaka07','우메다 스카이 전망대','주요시설',1500),('8106osaka08','오사카주택박물관','문화체험시설',600),('8106osaka09','센니치마에 도구야스지','쇼핑거리',0),('8106osaka10','도톤보리 리버크루즈','주요시설',900),('8111sapporo01','스스키노','주요거리',0),('8111sapporo02','삿포로 맥주 박물관','문화체험시설',0),('8111sapporo03','삿포로 텔레비전 타워','주요시설',720),('8111sapporo04','오도리 공원','주요거리',0),('8111sapporo05','다이마루','쇼핑/잡화시설',0),('8111sapporo06','훗카이도 대학','대학',0),('8111sapporo07','시로이코이비토 파크','주요명소',0),('8111sapporo08','삿포로 시계탑','주요명소',200),('8111sapporo09','훗카이도청 구 본청사','주요명소',0),('8111sapporo10','JR타워 전망대','주요시설',720),('8192fukuoka01','오호리 코엔 정원','주요명소',240),('8192fukuoka02','커낼시티','주요명소',0),('8192fukuoka03','나카스 포장마차거리','주요거리 및 맛집',0),('8192fukuoka04','모모치 해변','주요명소',0),('8192fukuoka05','후쿠오카 타워','주요명소',800),('8192fukuoka06','하우스텐보스','테마파크',6900),('8192fukuoka07','마리아노 시티','주요명소',0),('8192fukuoka08','마린 월드','주요명소',2300),('8192fukuoka09','라쿠스이엔','주요명소',100),('8192fukuoka10','아사히 맥주공장','주요명소',0),('8198okinawa01','아메리칸 빌리지','주요명소',0),('8198okinawa02','슈리성','주요명소',820),('8198okinawa03','츄마우리 수족관','주요명소',1850),('8198okinawa04','오키나와 월드','테마파크',1850),('8198okinawa05','오리온 맥주공장','체험문화시설',0),('8198okinawa06','비세노 후쿠기나미키','주요명소',0),('8198okinawa07','세이화우타키','주요명소',300),('8198okinawa08','이케이비치','주요명소',0),('8198okinawa09','류큐무라 민속촌','체험문화시설',1200),('8198okinawa10','무라사키무라','체험문화시설',600);
/*!40000 ALTER TABLE `tourlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'projectdb'
--

--
-- Dumping routines for database 'projectdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-22 16:20:50
