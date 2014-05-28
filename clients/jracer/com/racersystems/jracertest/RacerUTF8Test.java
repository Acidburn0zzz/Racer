package com.racersystems.jracertest;
import com.racersystems.jracer.RacerClient;

public class RacerUTF8Test {

	public static void main(String[] args) {

		// please adjust this path to match your environment: 

		// String jFamily = "\"C:/jracer-2-0/demo/family-j-utf8.racer\""; 

		String jFamily = "\"/home/mi.wessel/racer-support/jracer-2-0/demo/family-j-utf8.racer\""; 

		// UTF-8 Test
		// (Note: RacerPro must be running in UTF8 mode for this! 
		// Start as "RacerPro -- -ef @UTF8" 
		// Thanks to Mr. Kuroda Hisao for providing this japanese version
		// of the family.racer KB 

		String ip = "localhost";
		int port = 8088;

		RacerClient racer = new RacerClient(ip,port);		

		try {

			racer.openConnection();

			racer.loggingOn();
			racer.fullReset();
			racer.racerReadFile(jFamily); 
			System.out.println(racer.taxonomy()); 

			racer.fullReset(); 
			racer.inKnowledgeBaseM("家�?","鈴木家"); 
			racer.signatureM(":atomic-concepts",
					"(ヒト 人間 雌 雄 女 男 親 �? 父 祖�? 伯�? �?�父 姉妹 兄弟)",
					":roles", 
					"((�?孫を�?�?� :transitive t) (�?供を�?�?� :parent �?孫を�?�?�) �??ょ�?��?��?�を�?�?� (姉妹を�?�?� :parent �??ょ�?��?��?�を�?�?�) (兄弟を�?�?� :parent �??ょ�?��?��?�を�?�?�) (性を�?�?� :feature t))",
					":individuals",
					"(�?�よ �?��?� �?��?�?� ゆ�?? �?��?)"); 

			racer.impliesM("*top*","(all �?供を�?�?� 人間)"); 	    
			racer.impliesM("(some �?供を�?�?� *top*)","親");	    
			racer.impliesM("(some �??ょ�?��?��?�を�?�?� *top*)","(or 姉妹 兄弟)");
			racer.impliesM("*top*","(all �??ょ�?��?��?�を�?�?� (or 姉妹 兄弟))"); 
			racer.impliesM("*top*","(all 姉妹を�?�?� (some 性を�?�?� 雌))"); 
			racer.impliesM("*top*","(all 兄弟を�?�?� (some 性を�?�?� 雄))"); 
			racer.impliesM("人間","(and ヒト (some 性を�?�?� (or 雌 雄)))");
			racer.disjointM("雌","雄"); 
			racer.impliesM("女","(and 人間 (some 性を�?�?� 雌))"); 
			racer.impliesM("男","(and 人間 (some 性を�?�?� 雄))"); 
			racer.equivalentM("親","(and 人間 (some �?供を�?�?� 人間))"); 
			racer.equivalentM("�?","(and 女 親)"); 
			racer.equivalentM("父","(and 男 親)"); 
			racer.equivalentM("祖�?","(and �? (some �?供を�?�?� (some �?供を�?�?� 人間)))"); 
			racer.equivalentM("伯�?","(and 女 (some �??ょ�?��?��?�を�?�?� 親))"); 
			racer.equivalentM("�?�父","(and 男 (some �??ょ�?��?��?�を�?�?� 親))"); 
			racer.equivalentM("兄弟","(and 男 (some �??ょ�?��?��?�を�?�?� 人間))"); 
			racer.equivalentM("姉妹","(and 女 (some �??ょ�?��?��?�を�?�?� 人間))"); 
			racer.instanceM("�?�よ","�?"); 
			racer.relatedM("�?�よ","�?��?�","�?供を�?�?�"); 
			racer.relatedM("�?�よ","�?��?�?�","�?供を�?�?�"); 
			racer.instanceM("�?��?�","�?"); 
			racer.relatedM("�?��?�","ゆ�??","�?供を�?�?�"); 
			racer.relatedM("�?��?�","�?��?","�?供を�?�?�"); 
			racer.instanceM("�?��?�?�","兄弟"); 
			racer.relatedM("�?��?�?�","�?��?�", "�??ょ�?��?��?�を�?�?�"); 
			racer.instanceM("�?��?�?�","(at-most 1 �??ょ�?��?��?�を�?�?�)"); 
			racer.relatedM("ゆ�??","�?��?","姉妹を�?�?�"); 
			racer.relatedM("�?��?","ゆ�??","姉妹を�?�?�"); 

			System.out.println(racer.taxonomy()); 


		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}


