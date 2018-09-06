package base.Interact;

import CustomException.StringException;

/**
 * @author wmlbuaa
 * @date 2018-08-31 16:23
 */
public class LegalInformationJudge {
    public boolean judge(String str) throws StringException {
        if (str.length() >= 10000) return false;
        char[] c = str.toCharArray();
        for (int i = 0; i <= str.length(); i++) {
            if (!(c[i] <= 'z' && c[i] >= 'a') && !(c[i] <= 'Z' && c[i] >= 'A') && !(c[i] <= '9' && c[i] >= '0')) {
                return false;
            }
        }
        return true;
    }
}
