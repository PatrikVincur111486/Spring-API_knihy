package sk.stuba.fei.uim.oop.assignment3.author.authorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.authorController.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.authorData.Author;
import sk.stuba.fei.uim.oop.assignment3.author.authorData.IAuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.book.bookData.Book;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

@Service
public class AuthorService implements IAuthorService{

    @Autowired
    private IAuthorRepository repository;

    @Override
    public List<Author> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Author create(AuthorRequest request) {
        return this.repository.save(new Author(request));
    }

    @Override
    public Author getById(Long id) throws NotFoundException {
        Author a = this.repository.findAuthorById(id);
        if (a == null) {
            throw new NotFoundException();
        }
        return a;
    }

    @Override
    public Author update(Long id, AuthorRequest request) throws NotFoundException {
        Author a = this.getById(id);
        if (request.getName() != null) {
            a.setName(request.getName());
        }
        if (request.getName() != null) {
            a.setName(request.getName());
        }
        if (request.getSurname() != null) {
            a.setSurname(request.getSurname());
        }
        return this.repository.save(a);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Author a = this.getById(id);
        if(a==null){
            throw new NotFoundException();
        }
        this.repository.delete(this.getById(id));
    }

    @Override
    public void deleteBook(Book b) throws NotFoundException {
        Author a = this.repository.findAuthorById(b.getAuthor());
        if(a == null){
            throw new NotFoundException();
        }
        a.getBooks().remove(b);
    }
}
