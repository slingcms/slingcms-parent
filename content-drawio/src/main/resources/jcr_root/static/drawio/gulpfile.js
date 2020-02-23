const gulp        = require('gulp');
const sass        = require('gulp-sass');
const header      = require('gulp-header');
const cleanCSS   = require('gulp-clean-css');
const rename =  require('gulp-rename');
var concatCss = require('gulp-concat-css');
var run = require('gulp-run');
const concat = require('gulp-concat');
const terser = require('gulp-terser');
var replace = require('gulp-replace');
const streamqueue = require('streamqueue');
var fs = require('fs');

function srcDir(sub) {
    return `./js/${sub}`;
}
function distDir(sub) {
    return `./js/${sub}`;
}

//***********************************************
//conversion of build.xml from drawio
//***********************************************

var toolsPath="../../../../../../tools";

//merge all stencils
gulp.task('build-shapes-js-join', () => gulp.src([
    'shapes/**/*.js',
    'stencils/**/*.js'
    ])
    .pipe(concat('shapes.js'))
    .pipe(gulp.dest('./build/')));

gulp.task('build-stencils-compile', function() {
    return run(" java -cp " + toolsPath + " Xml2Js ./stencils/ ./build/stencils.min.js").exec();
});

gulp.task('build-compiles-shapes', function() {
    return run(" java -jar " + toolsPath +"/compiler.jar --js=./build/shapes.js --compilation_level=simple --warning_level DEFAULT --js_output_file=./build/shapes.min.js").exec();
});

gulp.task('build-declaration', () => gulp.src(['static/*.js'])
    .pipe(gulp.dest('./build/')));

gulp.task('build-style-default', function(done){
    gulp.src(['./styles/default.xml'])
        .pipe(replace(/\t/g, ''))
        .pipe(replace(/'/g, "\\\\'"))
        .pipe(rename('style.xml'))
        .pipe(gulp.dest('./build/'));
    done();
});

// gulp.task('styles', () =>
//     gulp.src(['./styles/default.xml'])
//         .pipe(replace(/\t/g, ''))
//         .pipe(replace(/'/g, "\\\\'")),
//     fs.writeFile('filename.txt', 'Graph.prototype.defaultThemes[\'default-style2\'] = mxUtils.parseXml(`', cb),
//     )
//     .pipe(rename('theme.xml'))
//     .pipe(gulp.dest('./build/'))
//     );

gulp.task('build-move-files', () => gulp.src([srcDir('build/*')])
    .pipe(gulp.dest(distDir(''))));


gulp.task('default', gulp.series('build-stencils-compile', 'build-shapes-js-join', 'build-compiles-shapes', 'build-style-default', 'build-declaration'));


//
// gulp.task('starter-styles', () => gulp.src(srcDir('scss/*.scss'))
//     .pipe(sass().on('error', sass.logError))
//     .pipe(cleanCSS())
//     .pipe(concatCss('bundle.css'))
//     .pipe(gulp.dest(distDir('jcr_root/content/starter/css'))));
//
// gulp.task('starter-assets', () => gulp.src(srcDir('img/*'))
//     .pipe(gulp.dest(distDir('jcr_root/content/starter/img'))));
//
// gulp.task('starter-fonts', () => gulp.src([srcDir('fonts/*')])
//     .pipe(gulp.dest(distDir('jcr_root/content/starter/fonts'))));
//
//
// gulp.task('starter-logo', () => gulp.src(srcDir('img/sling-logo.svg'))
//     .pipe(gulp.dest(distDir('jcr_root/content/starter'))));
//
// gulp.task('default', gulp.series('starter-styles', 'starter-assets', 'starter-fonts', 'starter-logo'));


