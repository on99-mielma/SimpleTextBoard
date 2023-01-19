let url = "http://192.168.31.17:8999/tb/"
const App = {
    data(){
        return{
            theDate:new Date().toLocaleString(),
            textboarddata:{
                code:0,
                msg:'',
                data:[]
            },
            mip:''
        }
    },
    methods:{
        UTCtoGMT8(dateForm){
            if (dateForm === "") {  //解决deteForm为空传1970-01-01 00:00:00
                return "";
            }else{
                let dateee = new Date(dateForm).toJSON();
                return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
            }
        },
        getAllText(){
            axios({
                url:url,
                method:"GET",
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8',
                    'Access-Control-Allow-Origin':'*'
                }
            }).then((res)=>{
                this.textboarddata.code = res.data.code;
                this.textboarddata.msg = res.data.msg;
                this.textboarddata.data = res.data.data;
                console.log("--------------------------")
                console.log(res)
                console.log("--------------------------")
            })
        },
        OSInfo(){
            let u = navigator.userAgent;
            if (!!u.match(/compatible/i) || u.match(/Windows/i)) {
                return 'Windows';
            } else if (!!u.match(/Macintosh/i) || u.match(/MacIntel/i)) {
                return 'MacOS';
            } else if (!!u.match(/iphone/i) || u.match(/Ipad/i)) {
                return 'IOS';
            } else if (!!u.match(/android/i)) {
                return 'Android';
            } else {
                return 'Other';
            }
        },
        UserIpInfo(stIP){
            let connection = window.RTCPeerConnection||window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
            let pc = new connection({
                iceServers:[]
            });
            let noop = () =>{};
            let localIPs = {};
            let ipRegex = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/g;
            let iterateIP = (ip) =>{
                if(!localIPs[ip]) stIP(ip);
                localIPs[ip] = true;
            };
            pc.createDataChannel('');
            pc.createOffer().then((sdp)=>{
                sdp.sdp.split('\n').forEach(function (line){
                    if(line.indexOf('candidate')<0) return;
                    line.match(ipRegex).forEach(iterateIP);
                });
                pc.setLocalDescription(sdp,noop,noop);
            }).catch((reason)=>{

            });
            pc.onicecandidate = (ice) =>{
                if (!ice||!ice.candidate||!ice.candidate.candidate||!ice.candidate.candidate.match(ipRegex)) return;
                ice.candidate.candidate.match(ipRegex).forEach(iterateIP);
            };
        },
        insertText(){
            let text = document.getElementById('insertinput').value;
            let tmpDate = this.UTCtoGMT8(new Date());
            let author = this.OSInfo();
            let p ={
                'text':text,
                'date':tmpDate,
                'author':author
            }
            axios({
                url:url,
                method: "POST",
                data:JSON.stringify(p),
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).then((res)=>{
                console.log(res)
                this.getAllText();
                document.getElementById('insertinput').value='';
            })
        }
    },
    beforeMount() {
        console.log(this.theDate)
        this.getAllText();
        this.UserIpInfo((ip)=>{
            this.mip = ip;
        });
        console.log('mip>')
        console.log(this.mip)
        console.log(this.OSInfo())
    }
}
const app = Vue.createApp(App)
app.mount("#MainPage")
