# AATODEV2_RENK_IYILESTIRME_ALGORITMASI
Algoritma Analizi ve Tasarımı Ödevi

Bu renk iyileştirme algoritması koda yüklediğiniz fotoğrafın renklerini daha belirgin ve canlı bir şekle dönüştürerek iyileştirme gerçekleştirmektedir.

Renk iyileştirme algoritmalarının çalışma prensiplerine göre değişse de genellikle zaman karmaışlıkları için şunlar söylenebilir:

  -En iyi zaman karmaşıklığı :
      O(n) olacaktır. Bu, algoritmanın her pikseli ayrı ayrı işlediği ve piksel sayısına doğrudan bağlı olduğu anlamına gelir.

  -En kötü zaman karmaşıklığı : 
      O(n^2) veya daha yüksek olabilir. Bu, algoritmanın işleme tabi tutulan görüntünün boyutuna ve uygulamanın karmaşıklığına bağlıdır. Örneğin, bazı renk iyileştirme         algoritmaları, tüm pikselleri birden işleyerek O(n^2) zaman karmaşıklığına sahip olabilirler.

  -Orta zaman karmaşıklığı :
      Genellikle O(n log n) veya O(n * m) şeklinde olabilir. Bu, algoritmanın uygulanmasında kullanılan tekniklere bağlıdır. Örneğin, bir renk iyileştirme algoritması,         bir görüntüyü önce segmente ederek O(n log n) zaman karmaşıklığına sahip olabilir.
      
Benim kullandığım algoritmaya gelicek isek benim kullandığım algoritma her pikseli tek tek gezme çalışma prensibine sahip olduğundan zaman karmaşıklığı O(n)'dir.
