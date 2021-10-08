# Java JPA Spring RestApi VetClinic Project

## Kullanılan Diller ve Yetenekler

| Dil / Yetenek|
| ------------- |
| Java       | 
| SpringBOOT    |
| Spring Security    | 
| MySQL        | 
| Spring Data JPA      |
| Spring Web Service   | 
| Regex      | 


## Uygulama Açıklaması
    
Bu uygulama Veteriner Kliniklerinin yönetimini sağlayan web siteleri için tasarlandı. Kullanıcı kişiler müşteri, müşterilerin evcil hayvanları ve tedarikçilerini sisteme
kaydederek belirli bir düzen oluşturabilirler. Formlarda bilgiler kaydedilirken kritik bölümlerde Validation kullanılmıştır. Örneğin Müşteri ismi, evcil hayvan
ismi girilirken boş bırakılmayacağına yönelik uyarılar gelmektedir ve sistem bu koşullar altında kayıt yapmamaktadır.

Uygulamayı kullanan kişiler için çeşitli roller vardır bunlar; USER, CUSTOMER, ADMIN VE SUPERADMIN.
SUPERADMIN yetkisine sahip kişiler tüm işlemleri yapma imkanına sahiptir. CUSTOMER yetkisi ileride kullanılma ihtimaline yönelik eklenmiştir.


| Web| Customer   | Admin | Super Admin |
| ------------- |:-------------:|:-------------:|:-------------:|
| Customer       |   |    |  X  |
| Buying         |   | X  |  X  |
| Sale           |   | X  |  X  |
| Pet            |   |    |  X  |

Sistemde Swagger desteği de bulunmaktadır. Haritalama türleri ve somut sınıflar için detaylı bilgiler verilmiştir.

Testlerin kolaylıkla yapılabilmesi için Postman Koleksiyonu da proje dosyalarında mevcuttur.

Herhangi bir sayfaya kullanıcı girişi yapmadan erişim sağlanamaz.


Not: Sistemi kullanabilmek için giriş yapmak gerekmektedir. Bu kullanıcı SUPERADMIN rolünde olup tüm yetkilere sahiptir.

| Kullanıcı Adı | Şifre   |Rol |
| ------------- |:-------------:|:-------------:|
|   ``` ferhat@mail.com```  | 12   | Super Admin  |



## Uygulama Görselleri

<p>
<a href="https://github.com/FerhatYildirim0/Java-JPA-Spring-RestApi-VetClinic-Project/blob/main/Images/Swagger%20Giri%C5%9F%20Sayfas%C4%B1.png" target="_blank">
<img src="https://github.com/FerhatYildirim0/Java-JPA-Spring-RestApi-VetClinic-Project/blob/main/Images/Swagger%20Giri%C5%9F%20Sayfas%C4%B1.png" width="200" style="max-width:100%;"></a>
  

<a href="https://github.com/FerhatYildirim0/Java-JPA-Spring-RestApi-VetClinic-Project/blob/main/Images/Servisler.png" target="_blank">
<img src="https://github.com/FerhatYildirim0/Java-JPA-Spring-RestApi-VetClinic-Project/blob/main/Images/Servisler.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/FerhatYildirim0/Java-JPA-Spring-RestApi-VetClinic-Project/blob/main/Images/Servisler%20-%20Mapping.png" target="_blank">
<img src="https://github.com/FerhatYildirim0/Java-JPA-Spring-RestApi-VetClinic-Project/blob/main/Images/Servisler%20-%20Mapping.png" width="200" style="max-width:100%;"></a>
  
  
<a href="https://github.com/FerhatYildirim0/Java-JPA-Spring-RestApi-VetClinic-Project/blob/main/Images/Servisler%20-%20Mapping%20Detay%C4%B1.png" target="_blank">
<img src="https://github.com/FerhatYildirim0/Java-JPA-Spring-RestApi-VetClinic-Project/blob/main/Images/Servisler%20-%20Mapping%20Detay%C4%B1.png" width="200" style="max-width:100%;"></a>
  

  <a href="https://github.com/FerhatYildirim0/Java-JPA-Spring-RestApi-VetClinic-Project/blob/main/Images/S%C4%B1n%C4%B1flar%20-%20Detay.png" target="_blank">
<img src="https://github.com/FerhatYildirim0/Java-JPA-Spring-RestApi-VetClinic-Project/blob/main/Images/S%C4%B1n%C4%B1flar%20-%20Detay.png" width="200" style="max-width:100%;"></a>
  
<a href="https://github.com/FerhatYildirim0/Java-JPA-Spring-RestApi-VetClinic-Project/blob/main/Images/S%C4%B1n%C4%B1flar%20-%20Detay.png" target="_blank">
<img src="https://github.com/FerhatYildirim0/Java-JPA-Spring-RestApi-VetClinic-Project/blob/main/Images/S%C4%B1n%C4%B1flar%20-%20Detay.png" width="200" style="max-width:100%;"></a>
