(this["webpackJsonpdarzelis-react"]=this["webpackJsonpdarzelis-react"]||[]).push([[8],{427:function(e,t,a){"use strict";a.r(t),a.d(t,"default",(function(){return h}));var n=a(4),r=a(5),c=a(8),l=a(7),i=a(0),s=a.n(i),o=a(2),m=a(1),u=a.n(m),h=(a(20),function(e){Object(c.a)(a,e);var t=Object(l.a)(a);function a(){var e;return Object(n.a)(this,a),(e=t.call(this)).componentDidMount=function(){return u.a.get(o.a+"/api/users/archive").then((function(t){return e.setState({archives:t.data})})).catch((function(e){403===e.response.status&&alert("J\u016bs neturite prieigos teisi\u0173 \u012f \u0161it\u0105 puslap\u012f. jei manote, kad tai klaida - prisijunkite i\u0161 naujo")})),function(){!1}},e.handleSearch=function(t){t.preventDefault();var a=t.target.value;e.setState({searchTerm:a})},e.state={archives:[],searchTerm:""},e}return Object(r.a)(a,[{key:"render",value:function(){var e=this,t=this.state.archives.filter((function(t){return-1!==t.email.toLowerCase().indexOf(e.state.searchTerm)}));return s.a.createElement("div",{className:"container mt-5"},s.a.createElement("div",{className:"mb-4"},s.a.createElement("h4",null," ","I\u0161trint\u0173 t\u0117v\u0173/glob\u0117j\u0173 ir \u0161vietimo specialist\u0173 paskyr\u0173 archyvai")),s.a.createElement("div",{className:"mb-4"},s.a.createElement("input",{className:"form-control mt-3 col-4",placeholder:"Paie\u0161ka pagal el.pa\u0161t\u0105",type:"text",name:"searchTerm",value:this.state.searchTerm,onChange:this.handleSearch}),s.a.createElement("table",{className:"table mt-4"},s.a.createElement("thead",null,s.a.createElement("tr",null,s.a.createElement("th",{scope:"col"},"#"),s.a.createElement("th",{scope:"col"},"El.pa\u0161tas"),s.a.createElement("th",{scope:"col"},"I\u0161trynimo data"),s.a.createElement("th",{scope:"col"},"Failo pavadinimas"),s.a.createElement("th",{scope:"col"},"Atsis\u0173sti"))),this.state.archives&&this.state.archives.length>0?t.map((function(e,t){var a=e.id,n=e.deletionDate,r=e.email,c=e.filename;return s.a.createElement("tbody",{key:a},s.a.createElement("tr",{key:a},s.a.createElement("th",{scope:"row"},t+1),s.a.createElement("td",null,r),s.a.createElement("td",null," ",n),s.a.createElement("td",null," ",c),s.a.createElement("td",null,s.a.createElement("a",{href:"".concat(o.a,"/api/users/archive/").concat(a,"/download"),rel:"noopener noreferrer",target:"_blank"},"Atsis\u0173sti"))))})):s.a.createElement("tbody",null,s.a.createElement("tr",null,s.a.createElement("td",null,"Loading..."))))))}}]),a}(i.Component))}}]);
//# sourceMappingURL=8.174f9f10.chunk.js.map