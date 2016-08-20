|  __ \          | |                 |  ___| |   (_)      | |  | |    | |     /  __ \ (_)          | |   | ___ \        (_)         | |  
| |  \/ __ _  ___| |__   ___  _ __   | |_  | |__  _ _ __  | |  | | ___| |__   | /  \/ |_  ___ _ __ | |_  | |_/ / __ ___  _  ___  ___| |_ 
| | __ / _` |/ __| '_ \ / _ \| '_ \  |  _| | '_ \| | '__| | |/\| |/ _ \ '_ \  | |   | | |/ _ \ '_ \| __| |  __/ '__/ _ \| |/ _ \/ __| __|
| |_\ \ (_| | (__| | | | (_) | | | | | |   | | | | | |    \  /\  /  __/ |_) | | \__/\ | |  __/ | | | |_  | |  | | | (_) | |  __/ (__| |_ 
 \____/\__,_|\___|_| |_|\___/|_| |_| \_|   |_| |_|_|_|     \/  \/ \___|_.__/   \____/_|_|\___|_| |_|\__| \_|  |_|  \___/| |\___|\___|\__|
                                                                                                                       _/ |              
                                                                                                                      |__/              
> Gachon HL7 FHIR Web Client for Healthcare Service. - hwanghj@gachon.ac.kr
>
> _“Courage. We all suffer. Keep going” (Graeme Fife)_

현재 문서를 샘플로 참고해서 Markdown 을 이용해 추후 문서를 업데이트 해야함! [contribution guidelines](contributing.md).

미래부 ICT힐링 프로젝트에 대한 상세한 정보는 [Awesome Command Line Apps](https://github.com/herrbischoff/awesome-command-line-apps).

## Table of Contents

- [개발팀 - 필독](#start)
    - [코딩 규칙](#codingconventions)
    - [개발 절차](#devprocess)
- [Applications](#applications)
    - [App Store](#app-store)
    - [Apple Remote Desktop](#apple-remote-desktop)
    - [Contacts](#contacts)
    - [Google](#google)
    - [iTunes](#itunes)
    - [Mail](#mail)
    - [Safari](#safari)
    - [Sketch](#sketch)
    - [Skim](#skim)
    - [TextEdit](#textedit)


## 개발팀 - 필독

### 코딩 규칙

#### 주석 처리
```bash
# 클래스 부
/**
 * Basic Controller Example
 * <HR>
 * 본 클래스는 컨트롤러 예제로 파라미터 처리와 모델 객체 사용을 보여준다. 사용법은 다음과 같다
 * <ul>
 *     <li>http://localhost:8080/hello</li>
 *     <li>http://localhost:8080/hello/name=hong</li>
 * </ul>
 *
 * @see com.dinfree.fhir.web.FhirWebClientApplication
 * @author Hee Joung Hwang(hwanghj@gachon.ac.kr)
 */
```
```bash
# 메서드 부
    /**
     * Basic Request Mapping Example
     * @param name
     * @param model
     * @return
     *
     * 사용법은 다음과 같다
     * <ul>
     *     <li>http://localhost:8080/hello</li>
     *     <li>http://localhost:8080/hello/name=hong</li>
     * </ul>
     */
    @RequestMapping("/hello")
    String index(@RequestParam(value="name", required=false, defaultValue="HITLAB") String name, Model model) {
        model.addAttribute("name", name);
        return "test";
    }
```





## License

<a rel="license" href="https://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://licensebuttons.net/l/by-sa/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="https://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Attribution-ShareAlike 4.0 International License</a>.
