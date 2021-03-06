package com.jccdex.rpc.api;

import org.junit.Test;
import org.mockito.Mockito;

import com.jccdex.rpc.base.JCallback;
import com.jccdex.rpc.url.JccdexUrl;

public class JccExploreTest {

	public final String host = "swtcscan.jccdex.cn";
	public JccdexUrl jccUrl = new JccdexUrl(host, true);

	JccExplore explore = JccExplore.getInstance();
	public JCallback mockCallBack;

	@Test
	public void testRequestBalance() {
		explore.setmBaseUrl(jccUrl);
		mockCallBack = Mockito.mock(JCallback.class);
		explore.requestBalance("uuid", "jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
	}

	@Test
	public void testRequestTransDetails() {
		explore.setmBaseUrl(jccUrl);
		mockCallBack = Mockito.mock(JCallback.class);
		explore.requestTransDetails("uuid", "DB1C9DF2224F3053746052E2790FB202B89E124EAD49356F6B8E3BDC4ACE6551",
				mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
	}

	@Test
	public void testRequestHistoricTransWithAddr() {
		explore.setmBaseUrl(jccUrl);
		mockCallBack = Mockito.mock(JCallback.class);
		explore.requestHistoricTransWithAddr("uuid", 0, 20, "", "", "", "", "jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6",
				mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// hash beign
		mockCallBack = Mockito.mock(JCallback.class);
		explore.requestHistoricTransWithAddr("uuid", 0, 20, "2018-06-01", "", "", "",
				"jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// hash end
		mockCallBack = Mockito.mock(JCallback.class);
		explore.requestHistoricTransWithAddr("uuid", 0, 20, "2018-06-01", "2018-06-21", "", "",
				"jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// hash type
		mockCallBack = Mockito.mock(JCallback.class);
		explore.requestHistoricTransWithAddr("uuid", 0, 20, "2018-06-01", "2018-06-21", "Send,Receive", "",
				"jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());

		// hash currency
		mockCallBack = Mockito.mock(JCallback.class);
		explore.requestHistoricTransWithAddr("uuid", 0, 20, "2018-06-01", "2018-06-21", "Send,Receive", "JJCC",
				"jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.anyString(), Mockito.anyString());
	}

	@Test
	public void testRequestPaymentSummary() {
		explore.setmBaseUrl(jccUrl);
		mockCallBack = Mockito.mock(JCallback.class);
		explore.requestPaymentSummary("uuid", "jM46vAHoWqg7NMvWqJzn3DEjXyAaXRzSGC", 2, "2019-01-01", "", "", "",
				mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.matches("0"), Mockito.anyString());

		mockCallBack = Mockito.mock(JCallback.class);
		explore.requestPaymentSummary("uuid", "jM46vAHoWqg7NMvWqJzn3DEjXyAaXRzSGC", 2, "2019-01-01", "2019-02-01", "",
				"", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.matches("0"), Mockito.anyString());

		mockCallBack = Mockito.mock(JCallback.class);
		explore.requestPaymentSummary("uuid", "jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 3, "2018-06", "2018-07", "", "",
				mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.matches("0"), Mockito.anyString());

		mockCallBack = Mockito.mock(JCallback.class);
		explore.requestPaymentSummary("uuid", "jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 4, "2018", "2019", "", "",
				mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.matches("0"), Mockito.anyString());

		mockCallBack = Mockito.mock(JCallback.class);
		explore.requestPaymentSummary("uuid", "jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 2, "2018-06-01", "2018-06-02",
				"Send", "", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.matches("0"), Mockito.anyString());

		mockCallBack = Mockito.mock(JCallback.class);
		explore.requestPaymentSummary("uuid", "jBvrdYc6G437hipoCiEpTwrWSRBS2ahXN6", 2, "2018-06-01", "2018-06-02",
				"Send", "SWTC_", mockCallBack);
		Mockito.verify(mockCallBack).onResponse(Mockito.matches("0"), Mockito.anyString());
	}

	@Test
	public void testRequestHistoricTransWithCur() {
		explore.setmBaseUrl(jccUrl);
		mockCallBack = new JCallback() {
			@Override
			public void onResponse(String code, String response) {
				System.out.println(response);

			}

			@Override
			public void onFail(Exception e) {
				System.out.println(e.getMessage());

			}
		};
		explore.requestHistoricTransWithCur("uuid", 0, 20, "", "", "Payment", "JJCC", mockCallBack);

		// no type
		explore.requestHistoricTransWithCur("uuid", 0, 20, "", "", "", "JJCC", mockCallBack);

		// hash beign
		explore.requestHistoricTransWithCur("uuid", 0, 20, "2018-06-01", "", "Payment", "", mockCallBack);

		// hash end;
		explore.requestHistoricTransWithCur("uuid", 0, 20, "2018-06-01", "2018-06-21", "Payment", "JJCC", mockCallBack);

		// hash type
		explore.requestHistoricTransWithCur("uuid", 0, 20, "2018-06-01", "2018-06-21", "Payment", "Send", mockCallBack);

		// hash currency
		explore.requestHistoricTransWithCur("uuid", 0, 20, "2018-06-01", "2018-06-21", "Payment", "JJCC", mockCallBack);
	}

}
