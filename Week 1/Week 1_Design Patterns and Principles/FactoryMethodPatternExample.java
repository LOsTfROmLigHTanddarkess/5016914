abstract class Document {
    public abstract void createDocument();
}

class ConcreteWordDocument extends Document {
    public void createDocument() {
        System.out.println("Created Word file");
    }
}

class ConcretePdfDocument extends Document {
    public void createDocument() {
        System.out.println("Created Pdf file");
    }
}

class ConcreteExcelDocument extends Document {
    public void createDocument() {
        System.out.println("Created Excel file");
    }
}

abstract class DocumentFactory {
    public abstract Document createDocument();
}

class ConcreteWordDoc extends DocumentFactory {
    public Document createDocument() {
        return new ConcreteWordDocument();
    }
}

class ConcretePdfDoc extends DocumentFactory {
    public Document createDocument() {
        return new ConcretePdfDocument();
    }
}

class ConcreteExcelDoc extends DocumentFactory {
    public Document createDocument() {
        return new ConcreteExcelDocument();
    }
}

public class FactoryMethodPatternExample 
{
    public static void main(String args[])
    {
        DocumentFactory worddocumentFactory = new ConcreteWordDoc();
        Document wordDocument = worddocumentFactory.createDocument();
        wordDocument.createDocument();

        DocumentFactory pdfDocumentFactory = new ConcretePdfDoc();
        Document pdfDocument = pdfDocumentFactory.createDocument();
        pdfDocument.createDocument();

        DocumentFactory excelDocumentFactory = new ConcreteExcelDoc();
        Document excelDocument = excelDocumentFactory.createDocument();
        excelDocument.createDocument();
    }
}
